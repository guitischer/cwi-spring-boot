package com.desafio.cooperativismo.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cooperativismo.dtos.PollDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.enums.PollStatusEnum;
import com.desafio.cooperativismo.enums.ResultEnum;
import com.desafio.cooperativismo.enums.VoteEnum;
import com.desafio.cooperativismo.exceptions.ApiException;
import com.desafio.cooperativismo.exceptions.InvalidParameterException;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.exceptions.ResourceNotFoundException;
import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.models.Topic;
import com.desafio.cooperativismo.repositories.PollRepository;
import com.desafio.cooperativismo.repositories.TopicRepository;

@Service
public class PollService {

  @Autowired
  PollRepository pollRepository;

  @Autowired
  TopicRepository topicRepository;

  public List<Poll> getPolls() {
    return pollRepository.findAll();
  }

  public void createPoll(PollDTO pollDTO) {
    var poll = new Poll();
    BeanUtils.copyProperties(pollDTO, poll);

    requiredTopicValidation(poll);
    checkIfTopicExists(pollDTO.getTopic().getId());
    validateTopicRelationship(pollDTO.getTopic());
    checkIfPollEndIsNotInThePast(poll);

    if (poll.getEndAt() == null) {
      LocalDateTime today = LocalDateTime.now().plus(Duration.of(1, ChronoUnit.MINUTES));
      poll.setEndAt(today);
    }

    pollRepository.save(poll);
  }

  public ResultEnum getResult(Long pollId) {
    Poll poll = pollRepository.findById(pollId)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageEnum.POLL_NOT_FOUND));
    Long upvotes = poll.getVotes().stream().filter(vote -> vote.getVote().equals(VoteEnum.YES)).count();
    Long downvotes = poll.getVotes().stream().filter(vote -> vote.getVote().equals(VoteEnum.NO)).count();
    ResultEnum result;

    if (upvotes > downvotes) {
      result = ResultEnum.APPROVED;
    } else if (upvotes < downvotes) {
      result = ResultEnum.DISAPPROVED;
    } else {
      result = ResultEnum.TIE;
    }

    return result;
  }

  public PollStatusEnum getStatus(Long pollId) {
    Poll poll = pollRepository.findById(pollId)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageEnum.POLL_NOT_FOUND));

    if (LocalDateTime.now().isAfter(poll.getEndAt())) {
      return PollStatusEnum.POLL_CLOSED;
    }

    return PollStatusEnum.POLL_OPENED;
  }

  private void checkIfPollEndIsNotInThePast(Poll poll) {
    if (poll.getEndAt() != null && poll.getEndAt().isBefore(LocalDateTime.now())) {
      throw new InvalidParameterException(ErrorMessageEnum.POLL_IN_PAST.getMessage());
    }
  }

  private void checkIfTopicExists(Long topicId) {
    Optional<Topic> topic = topicRepository.findById(topicId);
    if (!topic.isPresent()) {
      throw new ResourceNotFoundException(ErrorMessageEnum.TOPIC_NOT_FOUND);
    }
  }

  private void requiredTopicValidation(Poll poll) {
    if (poll.getTopic() == null) {
      throw new MissingParameterException(ErrorMessageEnum.REQUIRED_TOPIC_FIELD);
    }
  }

  private void validateTopicRelationship(Topic topic) {
    List<Poll> polls = pollRepository.findAllOpenedPollsByTopic(topic, LocalDateTime.now());
    if (polls.size() > 0) {
      throw new ApiException(ErrorMessageEnum.POLL_WITH_TOPIC_ALREADY_RUNNING);
    }
  }
}
