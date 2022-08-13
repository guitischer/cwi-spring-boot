package com.desafio.springboot.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.dtos.PollDTO;
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

    if (poll.getEndAt() == null) {
      LocalDateTime today = LocalDateTime.now().plus(Duration.of(1, ChronoUnit.MINUTES));
      poll.setEndAt(today);
    }

    pollRepository.save(poll);
  }

}
