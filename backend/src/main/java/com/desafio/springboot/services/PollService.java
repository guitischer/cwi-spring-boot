package com.desafio.springboot.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.models.Poll;
import com.desafio.springboot.repositories.PollRepository;

@Service
public class PollService {

  @Autowired
  PollRepository pollRepository;

  public void createPoll(Poll poll) {

    if (poll.getEndAt() == null) {
      LocalDateTime today = LocalDateTime.now().plus(Duration.of(1, ChronoUnit.MINUTES));
      poll.setEndAt(today);
    }

    pollRepository.save(poll);
  }
}
