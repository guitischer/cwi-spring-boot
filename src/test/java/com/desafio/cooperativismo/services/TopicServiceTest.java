package com.desafio.cooperativismo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.beans.BeanUtils;

import com.desafio.cooperativismo.dtos.TopicDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.models.Topic;
import com.desafio.cooperativismo.repositories.TopicRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TopicServiceTest {

  @InjectMocks
  private TopicService topicService;

  @Mock
  private TopicRepository topicRepository;

  @Test
  void getTopics_Success() {
    Topic topic1 = getTopicAllArgs();
    Topic topic2 = getTopicAllArgs();

    List<Topic> topicList = new ArrayList<>(Arrays.asList(topic1, topic2));
    when(topicRepository.findAll()).thenReturn(topicList);

    List<Topic> topics = topicService.getTopics();
    verify(topicRepository).findAll();

    assert (topics == topicList);
  }

  @Test
  void saveTopicAllArgs_Success() {
    TopicDTO topicDTO = new TopicDTO();
    BeanUtils.copyProperties(getTopicAllArgs(), topicDTO);

    topicService.createTopic(topicDTO);
    verify(topicRepository).save(any(Topic.class));
  }

  @Test
  void saveTopicWithoutDescription_Success() {
    TopicDTO topicDTO = new TopicDTO();
    BeanUtils.copyProperties(getTopicAllArgs(), topicDTO);
    topicDTO.setDescription(null);

    topicService.createTopic(topicDTO);
    verify(topicRepository).save(any(Topic.class));
  }

  @Test
  void saveTopicWithoutName_Fail() {
    MissingParameterException exception = Assertions.assertThrows(MissingParameterException.class, () -> {
      TopicDTO topicDTO = new TopicDTO();
      BeanUtils.copyProperties(getTopicAllArgs(), topicDTO);
      topicDTO.setName(null);

      when(topicRepository.findByName(topicDTO.getName())).thenReturn(null);

      topicService.createTopic(topicDTO);
      verify(topicRepository, never()).save(any(Topic.class));
    });

    assertTrue(exception.getMessage().contains(ErrorMessageEnum.REQUIRED_NAME_FIELD.getMessage()));
  }

  private static Topic getTopicAllArgs() {
    return Topic
        .builder()
        .id(new Random().nextLong())
        .name("Topic X")
        .description("Description for Topic X")
        .build();
  }

}
