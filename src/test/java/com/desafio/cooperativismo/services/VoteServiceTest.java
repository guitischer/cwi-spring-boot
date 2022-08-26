package com.desafio.cooperativismo.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.BeanUtils;

import com.desafio.cooperativismo.clients.CPFClient;
import com.desafio.cooperativismo.dtos.VoteDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.enums.PollStatusEnum;
import com.desafio.cooperativismo.enums.UserVoteEnum;
import com.desafio.cooperativismo.enums.VoteEnum;
import com.desafio.cooperativismo.exceptions.DuplicateRecordException;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.exceptions.ResourceNotFoundException;
import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.models.Topic;
import com.desafio.cooperativismo.models.User;
import com.desafio.cooperativismo.models.Vote;
import com.desafio.cooperativismo.repositories.PollRepository;
import com.desafio.cooperativismo.repositories.UserRepository;
import com.desafio.cooperativismo.repositories.VoteRepository;
import com.desafio.cooperativismo.responses.CPFResponse;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class VoteServiceTest {

  @InjectMocks
  VoteService voteService;

  @Mock
  VoteRepository voteRepository;

  @Mock
  UserRepository userRepository;

  @Mock
  PollRepository pollRepository;

  @Mock
  PollService pollService;

  @Mock
  CPFClient cpfClient;

  @Test
  void saveVoteAllArgs_Success() {
    Vote vote = getVoteAllArgs();
    VoteDTO voteDTO = new VoteDTO();
    voteDTO.setPollId(vote.getPoll().getId());
    voteDTO.setUserId(vote.getUser().getId());
    voteDTO.setVote(vote.getVote());

    User user = vote.getUser();
    when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

    CPFResponse possibilityToVote = CPFResponse.builder().status(UserVoteEnum.ABLE_TO_VOTE.getResponse())
        .build();
    when(cpfClient.getStatus(any(String.class))).thenReturn(possibilityToVote);

    Poll poll = vote.getPoll();
    when(pollRepository.findById(any(Long.class))).thenReturn(Optional.of(poll));

    when(pollService.getStatus(any(Long.class))).thenReturn(PollStatusEnum.POLL_OPENED);

    voteService.createVote(voteDTO);

    verify(voteRepository).save(any(Vote.class));
  }

  @Test
  void saveVoteWithoutVoteField_Fail() {
    MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
      VoteDTO voteDTO = new VoteDTO();
      BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);
      voteDTO.setVote(null);

      voteService.createVote(voteDTO);
      verify(voteRepository).save(any(Vote.class));
    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.REQUIRED_VOTE_FIELD.getMessage()));
  }

  @Test
  void saveVoteOfSameUser_Fail() {
    DuplicateRecordException exception = Assertions.assertThrows(DuplicateRecordException.class, () -> {
      Vote vote = getVoteAllArgs();
      VoteDTO voteDTO = new VoteDTO();
      voteDTO.setPollId(vote.getPoll().getId());
      voteDTO.setUserId(vote.getUser().getId());
      voteDTO.setVote(vote.getVote());

      User user = vote.getUser();
      when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

      CPFResponse possibilityToVote = CPFResponse.builder().status(UserVoteEnum.ABLE_TO_VOTE.getResponse())
          .build();
      when(cpfClient.getStatus(any(String.class))).thenReturn(possibilityToVote);

      Poll poll = vote.getPoll();
      when(pollRepository.findById(any(Long.class))).thenReturn(Optional.of(poll));

      when(pollService.getStatus(any(Long.class))).thenReturn(PollStatusEnum.POLL_OPENED);

      voteService.createVote(voteDTO);
      when(voteRepository.findOneByUserAndPoll(user, poll)).thenReturn(Optional.of(getVoteAllArgs()));
      when(voteRepository.save(any(Vote.class))).thenReturn(getVoteAllArgs());

      voteService.createVote(voteDTO);
      verify(voteRepository, times(2)).save(any(Vote.class));

    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.USER_HAVE_ALREADY_VOTED.getMessage()));
  }

  @Test
  void saveVoteWithoutUser_Fail() {
    MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
      Vote vote = getVoteAllArgs();
      VoteDTO voteDTO = new VoteDTO();
      voteDTO.setPollId(vote.getPoll().getId());
      voteDTO.setUserId(null);
      voteDTO.setVote(vote.getVote());

      voteService.createVote(voteDTO);
      verify(voteRepository).save(any(Vote.class));
    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.REQUIRED_USER_FIELD.getMessage()));
  }

  @Test
  void saveVoteWithoutPoll_Fail() {
    MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
      Vote vote = getVoteAllArgs();
      VoteDTO voteDTO = new VoteDTO();
      voteDTO.setUserId(vote.getUser().getId());
      voteDTO.setPollId(null);
      voteDTO.setVote(vote.getVote());

      voteService.createVote(voteDTO);
      verify(voteRepository).save(any(Vote.class));
    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.REQUIRED_POLL_FIELD.getMessage()));
  }

  @Test
  void saveVoteWithNonExistentPoll_Fail() {
    ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      Vote vote = getVoteAllArgs();
      VoteDTO voteDTO = new VoteDTO();
      voteDTO.setPollId(vote.getPoll().getId());
      voteDTO.setUserId(vote.getUser().getId());
      voteDTO.setVote(vote.getVote());

      User user = vote.getUser();
      when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

      CPFResponse possibilityToVote = CPFResponse.builder().status(UserVoteEnum.ABLE_TO_VOTE.getResponse())
          .build();
      when(cpfClient.getStatus(any(String.class))).thenReturn(possibilityToVote);

      when(pollService.getStatus(any(Long.class))).thenReturn(PollStatusEnum.POLL_OPENED);

      voteService.createVote(voteDTO);
      verify(voteRepository).save(any(Vote.class));
    });

    assertTrue(exception.getMessage().equals(ErrorMessageEnum.POLL_NOT_FOUND.getMessage()));
  }

  @Test
  void saveVoteWithNonExistentUser_Fail() {
    ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      Vote vote = getVoteAllArgs();
      VoteDTO voteDTO = new VoteDTO();
      voteDTO.setPollId(vote.getPoll().getId());
      voteDTO.setUserId(vote.getUser().getId());
      voteDTO.setVote(vote.getVote());

      Poll poll = vote.getPoll();
      when(pollRepository.findById(any(Long.class))).thenReturn(Optional.of(poll));

      voteService.createVote(voteDTO);
      verify(voteRepository).save(any(Vote.class));
    });

    assertTrue(exception.getMessage().equals(ErrorMessageEnum.USER_NOT_FOUND.getMessage()));
  }

  private static Vote getVoteAllArgs() {
    Topic topic = Topic
        .builder()
        .id(new Random().nextLong())
        .name("Topic X")
        .description("Description for Topic X")
        .build();

    Poll poll = Poll
        .builder()
        .id(new Random().nextLong())
        .topic(topic)
        .endAt(LocalDateTime.now().plus(Duration.of(1, ChronoUnit.MINUTES)))
        .build();

    User user = User
        .builder()
        .id(new Random().nextLong())
        .name("Dummy")
        .cpf("00000000001")
        .build();

    return Vote.builder().id(new Random().nextLong()).vote(VoteEnum.YES).user(user).poll(poll).build();
  }

}
