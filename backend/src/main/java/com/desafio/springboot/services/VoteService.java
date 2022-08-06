package com.desafio.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.models.Vote;
import com.desafio.springboot.repositories.VoteRepository;

@Service
public class VoteService {

  @Autowired
  VoteRepository voteRepository;

  public void createVote(Vote vote) {
    voteRepository.save(vote);
  }
}
