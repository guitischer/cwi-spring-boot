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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.BeanUtils;

import com.desafio.cooperativismo.clients.CPFClient;
import com.desafio.cooperativismo.dtos.VoteDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.enums.UserVoteEnum;
import com.desafio.cooperativismo.enums.VoteEnum;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.models.Topic;
import com.desafio.cooperativismo.models.User;
import com.desafio.cooperativismo.models.Vote;
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
  CPFClient cpfClient;

  @Test
  void saveVoteAllArgs_Success() {
    VoteDTO voteDTO = new VoteDTO();
    BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);

    User user = voteDTO.getUser();
    Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

    CPFResponse possibilityToVote = CPFResponse.builder().status(UserVoteEnum.ABLE_TO_VOTE).build();
    Mockito.when(cpfClient.getStatus(any(String.class))).thenReturn(possibilityToVote);

    voteService.createVote(voteDTO);

    Mockito.verify(voteRepository).save(any(Vote.class));
  }

  @Test
  void saveVoteWithoutVoteField_Fail() {
    MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
      VoteDTO voteDTO = new VoteDTO();
      BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);
      voteDTO.setVote(null);

      voteService.createVote(voteDTO);
      Mockito.verify(voteRepository).save(any(Vote.class));
    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.REQUIRED_VOTE_FIELD.getMessage()));
  }

  @Test
  void saveVoteWithoutUser_Fail() {
    MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
      VoteDTO voteDTO = new VoteDTO();
      BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);
      voteDTO.setUser(null);

      voteService.createVote(voteDTO);
      Mockito.verify(voteRepository).save(any(Vote.class));
    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.REQUIRED_USER_FIELD.getMessage()));
  }

  @Test
  void saveVoteWithoutPoll_Fail() {
    MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
      VoteDTO voteDTO = new VoteDTO();
      BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);
      voteDTO.setPoll(null);

      voteService.createVote(voteDTO);
      Mockito.verify(voteRepository).save(any(Vote.class));
    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.REQUIRED_POLL_FIELD.getMessage()));
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
