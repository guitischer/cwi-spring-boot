package com.desafio.cooperativismo.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.desafio.cooperativismo.dtos.PollDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.models.Topic;
import com.desafio.cooperativismo.repositories.PollRepository;
import com.desafio.cooperativismo.repositories.TopicRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PollServiceTest {

  @InjectMocks
  PollService pollService;

  @Mock
  PollRepository pollRepository;

  @Mock
  TopicRepository topicRepository;

  @Test
  void getPolls_Success() {
    Poll poll1 = getPollAllArgs();
    Poll poll2 = getPollAllArgs();

    List<Poll> pollList = new ArrayList<>(Arrays.asList(poll1, poll2));
    when(pollRepository.findAll()).thenReturn(pollList);

    List<Poll> polls = pollService.getPolls();
    verify(pollRepository).findAll();

    assert (polls == pollList);
  }

  @Test
  void savePollWithAllArgs_Success() {
    Poll poll = getPollAllArgs();
    PollDTO pollDTO = new PollDTO();
    pollDTO.setTopicId(poll.getTopic().getId());
    pollDTO.setEndAt(poll.getEndAt());

    Topic topic = poll.getTopic();
    when(topicRepository.findById(any(Long.class))).thenReturn(Optional.of(topic));

    pollService.createPoll(pollDTO);
    verify(pollRepository).save(any(Poll.class));
  }

  @Test
  void savePollWithoutEndAt_Success() {
    Poll poll = getPollAllArgs();
    PollDTO pollDTO = new PollDTO();
    pollDTO.setTopicId(poll.getTopic().getId());
    pollDTO.setEndAt(null);

    Topic topic = poll.getTopic();
    when(topicRepository.findById(any(Long.class))).thenReturn(Optional.of(topic));

    pollService.createPoll(pollDTO);
    verify(pollRepository).save(any(Poll.class));
  }

  @Test
  void savePollWithoutTopic_Fail() {
    MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
      Poll poll = getPollAllArgs();
      PollDTO pollDTO = new PollDTO();
      pollDTO.setTopicId(null);
      pollDTO.setEndAt(poll.getEndAt());

      pollService.createPoll(pollDTO);
      verify(pollRepository, never()).save(any(Poll.class));
    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.REQUIRED_TOPIC_FIELD.getMessage()));
  }

  private static Poll getPollAllArgs() {
    Topic topic = Topic
        .builder()
        .id(new Random().nextLong())
        .name("Topic X")
        .description("Description for Topic X")
        .build();

    return Poll
        .builder()
        .id(new Random().nextLong())
        .topic(topic)
        .endAt(LocalDateTime.now().plus(Duration.of(1, ChronoUnit.MINUTES)))
        .build();
  }

}
