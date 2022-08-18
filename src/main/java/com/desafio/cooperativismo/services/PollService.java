package com.desafio.cooperativismo.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cooperativismo.dtos.PollDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.enums.ResultEnum;
import com.desafio.cooperativismo.enums.VoteEnum;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.exceptions.ResourceNotFoundException;
import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.repositories.PollRepository;

@Service
public class PollService {

  @Autowired
  PollRepository pollRepository;

  public List<Poll> getPolls() {
    return pollRepository.findAll();
  }

  public void createPoll(PollDTO pollDTO) {
    var poll = new Poll();
    BeanUtils.copyProperties(pollDTO, poll);

    requiredTopicValidation(poll);

    if (poll.getEndAt() == null) {
      LocalDateTime today = LocalDateTime.now().plus(Duration.of(1, ChronoUnit.MINUTES));
      poll.setEndAt(today);
    }

    pollRepository.save(poll);
  }

  public ResultEnum getResult(Long pollId) {
    Poll poll = pollRepository.findById(pollId)
        .orElseThrow(() -> new ResourceNotFoundException("Sessão com id " + pollId + " não encontrada!"));
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

  private void requiredTopicValidation(Poll poll) {
    if (poll.getTopic() == null) {
      throw new MissingParameterException(ErrorMessageEnum.REQUIRED_TOPIC_FIELD);
    }
  }

}
