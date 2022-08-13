package com.desafio.springboot.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.dtos.VoteDTO;
import com.desafio.springboot.models.Vote;
import com.desafio.springboot.repositories.VoteRepository;

@Service
public class VoteService {

  @Autowired
  VoteRepository voteRepository;

  public List<Vote> getResult() {
    return voteRepository.findAll();
  }

  public void createVote(VoteDTO voteDTO) {
    var vote = new Vote();
    BeanUtils.copyProperties(voteDTO, vote);
    voteRepository.save(vote);
  }
}
