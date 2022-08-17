package com.desafio.springboot.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.clients.CPFClient;
import com.desafio.springboot.dtos.VoteDTO;
import com.desafio.springboot.enums.UserVoteEnum;
import com.desafio.springboot.exceptions.ApiException;
import com.desafio.springboot.exceptions.MissingParameterException;
import com.desafio.springboot.exceptions.ResourceNotFoundException;
import com.desafio.springboot.models.User;
import com.desafio.springboot.models.Vote;
import com.desafio.springboot.repositories.UserRepository;
import com.desafio.springboot.repositories.VoteRepository;
import com.desafio.springboot.responses.CPFResponse;

@Service
public class VoteService {

  @Autowired
  VoteRepository voteRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CPFClient cpfClient;

  public void createVote(VoteDTO voteDTO) {
    var vote = new Vote();
    BeanUtils.copyProperties(voteDTO, vote);
    requiredFieldsValidation(vote);
    checkIfUserIsAbleToVote(voteDTO.getUser().getId());
    voteRepository.save(vote);
  }

  private void checkIfUserIsAbleToVote(Long userId) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + userId + " não encontrado!"));
    CPFResponse possibilityToVote = cpfClient.getStatus(user.getCpf());

    if (possibilityToVote == null) {
      throw new ApiException("O CPF informado não é válido");
    } else if (possibilityToVote.getStatus().equals(UserVoteEnum.UNABLE_TO_VOTE)) {
      throw new ApiException("Esse usuário não pode votar");
    }

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
