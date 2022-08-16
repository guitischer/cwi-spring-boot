package com.desafio.springboot.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.dtos.VoteDTO;
import com.desafio.springboot.exceptions.MissingParameterException;
import com.desafio.springboot.models.Poll;
import com.desafio.springboot.models.Vote;
import com.desafio.springboot.repositories.VoteRepository;

@Service
public class VoteService {

  @Autowired
  VoteRepository voteRepository;

  public void createVote(VoteDTO voteDTO) {
    var vote = new Vote();
    BeanUtils.copyProperties(voteDTO, vote);
    requiredFieldsValidation(vote);
    voteRepository.save(vote);
  }

  private void requiredFieldsValidation(Vote vote) {
    if (vote.getVote() == null) {
      throw new MissingParameterException("Campo 'Vote' é obrigatório!");
    }

    if (vote.getUser() == null) {
      throw new MissingParameterException("Campo 'User' é obrigatório!");
    }

    if (vote.getPoll() == null) {
      throw new MissingParameterException("Campo 'Poll' é obrigatório!");
    }
  }
}
