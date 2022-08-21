package com.desafio.cooperativismo.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cooperativismo.clients.CPFClient;
import com.desafio.cooperativismo.dtos.VoteDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.enums.PollStatusEnum;
import com.desafio.cooperativismo.enums.UserVoteEnum;
import com.desafio.cooperativismo.exceptions.ApiException;
import com.desafio.cooperativismo.exceptions.DuplicateRecordException;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.exceptions.ResourceNotFoundException;
import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.models.User;
import com.desafio.cooperativismo.models.Vote;
import com.desafio.cooperativismo.repositories.PollRepository;
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
  PollRepository pollRepository;

  @Autowired
  PollService pollService;

  @Autowired
  CPFClient cpfClient;

  /**
   * Método para realizar a votação (Vote) na sessão de votação (Poll) criada a
   * partir da pauta (Topic).
   * 
   * @param voteDTO DTO (Data Transfer Object) de votação (Vote)
   * @throws MissingParameterException caso algum parâmetro obrigatório não seja
   *                                   enviado pelo DTO
   * @throws ResourceNotFoundException caso a usuário (User), CPF do usuário ou
   *                                   sessão de votação (Poll) não exista
   * @throws ApiException              caso a sessão de votação esteja fechada,
   *                                   não sendo possível realizar a votação
   */
  public void createVote(VoteDTO voteDTO) {
    var vote = new Vote();
    BeanUtils.copyProperties(voteDTO, vote);
    requiredFieldsValidation(vote);

    User user = checkIfUserExists(voteDTO.getUser().getId());
    Poll poll = checkIfPollExists(voteDTO.getPoll().getId());

    checkIfPollIsOpened(poll);

    checkIfUserHaveAlreadyVoted(user, poll);
    checkIfUserIsAbleToVote(user);

    voteRepository.save(vote);
  }

  private User checkIfUserExists(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageEnum.USER_NOT_FOUND));
  }

  private Poll checkIfPollExists(Long pollId) {
    return pollRepository.findById(pollId)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageEnum.POLL_NOT_FOUND));
  }

  private void checkIfPollIsOpened(Poll poll) {
    PollStatusEnum pollStatus = pollService.getStatus(poll.getId());

    if (pollStatus.equals(PollStatusEnum.POLL_CLOSED)) {
      throw new ApiException(ErrorMessageEnum.POLL_CLOSED);
    }
  }

  private void checkIfUserHaveAlreadyVoted(User user, Poll poll) {
    Optional<Vote> userVote = voteRepository.findOneByUserAndPoll(user, poll);
    if (userVote.isPresent()) {
      throw new DuplicateRecordException(ErrorMessageEnum.USER_HAVE_ALREADY_VOTED);
    }
  }

  private void checkIfUserIsAbleToVote(User user) {
    CPFResponse possibilityToVote = cpfClient.getStatus(user.getCpf());

    if (possibilityToVote == null) {
      throw new ResourceNotFoundException(ErrorMessageEnum.INVALID_CPF);
    } else if (possibilityToVote.getStatus().equals(UserVoteEnum.UNABLE_TO_VOTE.getResponse())) {
      throw new ApiException(ErrorMessageEnum.UNABLE_TO_VOTE);
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
