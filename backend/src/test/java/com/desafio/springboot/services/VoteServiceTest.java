package com.desafio.springboot.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafio.springboot.dtos.VoteDTO;
import com.desafio.springboot.enums.VoteEnum;
import com.desafio.springboot.exceptions.MissingParameterException;
import com.desafio.springboot.models.Poll;
import com.desafio.springboot.models.Topic;
import com.desafio.springboot.models.User;
import com.desafio.springboot.models.Vote;
import com.desafio.springboot.repositories.VoteRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class VoteServiceTest {

  @InjectMocks
  VoteService voteService;

  @Mock
  VoteRepository voteRepository;

  @Test
  void saveVoteAllArgs_Success() {
    VoteDTO voteDTO = new VoteDTO();
    BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);

    voteService.createVote(voteDTO);
    Mockito.verify(voteRepository).save(any(Vote.class));
  }

  @Test
  void saveVoteWithoutVoteField_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      VoteDTO voteDTO = new VoteDTO();
      BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);
      voteDTO.setVote(null);

      voteService.createVote(voteDTO);
      Mockito.verify(voteRepository).save(any(Vote.class));
    });
  }

  @Test
  void saveVoteWithoutUser_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      VoteDTO voteDTO = new VoteDTO();
      BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);
      voteDTO.setUser(null);

      voteService.createVote(voteDTO);
      Mockito.verify(voteRepository).save(any(Vote.class));
    });
  }

  @Test
  void saveVoteWithoutPoll_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      VoteDTO voteDTO = new VoteDTO();
      BeanUtils.copyProperties(getVoteAllArgs(), voteDTO);
      voteDTO.setPoll(null);

      voteService.createVote(voteDTO);
      Mockito.verify(voteRepository).save(any(Vote.class));
    });
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
