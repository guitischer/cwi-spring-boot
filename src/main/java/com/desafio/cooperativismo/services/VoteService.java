package com.desafio.cooperativismo.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cooperativismo.clients.CPFClient;
import com.desafio.cooperativismo.dtos.VoteDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.enums.UserVoteEnum;
import com.desafio.cooperativismo.exceptions.ApiException;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.exceptions.ResourceNotFoundException;
import com.desafio.cooperativismo.models.User;
import com.desafio.cooperativismo.models.Vote;
import com.desafio.cooperativismo.repositories.UserRepository;
import com.desafio.cooperativismo.repositories.VoteRepository;
import com.desafio.cooperativismo.responses.CPFResponse;

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
      throw new MissingParameterException(ErrorMessageEnum.REQUIRED_VOTE_FIELD);
    }

    if (vote.getUser() == null) {
      throw new MissingParameterException(ErrorMessageEnum.REQUIRED_USER_FIELD);
    }

    if (vote.getPoll() == null) {
      throw new MissingParameterException(ErrorMessageEnum.REQUIRED_POLL_FIELD);
    }
  }
}
