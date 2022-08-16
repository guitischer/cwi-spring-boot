package com.desafio.springboot.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.dtos.TopicDTO;
import com.desafio.springboot.exceptions.MissingParameterException;
import com.desafio.springboot.models.Topic;
import com.desafio.springboot.repositories.TopicRepository;

@Service
public class TopicService {

  @Autowired
  TopicRepository topicRepository;

  public List<Topic> getTopics() {
    return topicRepository.findAll();
  }

  public void createTopic(TopicDTO topicDTO) {
    var topic = new Topic();
    BeanUtils.copyProperties(topicDTO, topic);
    requiredNameValidation(topic);
    topicRepository.save(topic);
  }

  public void requiredNameValidation(Topic topic) {
    if (topic.getName() == null || topic.getName().equals("")) {
      throw new MissingParameterException("Campo 'Nome' é obrigatório!");
    }
  }
}
