package com.desafio.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.models.Poll;
import com.desafio.springboot.repositories.PollRepository;

@Service
public class PollService {

  @Autowired
  PollRepository pollRepository;

  public void createPoll(Poll poll) {
    pollRepository.save(poll);
  }
}
