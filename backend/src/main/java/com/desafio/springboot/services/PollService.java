package com.desafio.springboot.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.dtos.PollDTO;
import com.desafio.springboot.enums.ResultEnum;
import com.desafio.springboot.enums.VoteEnum;
import com.desafio.springboot.exceptions.MissingParameterException;
import com.desafio.springboot.exceptions.ResourceNotFoundException;
import com.desafio.springboot.models.Poll;
import com.desafio.springboot.repositories.PollRepository;

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
      throw new MissingParameterException("Campo 'Topic' é obrigatório!");
    }
  }

}
