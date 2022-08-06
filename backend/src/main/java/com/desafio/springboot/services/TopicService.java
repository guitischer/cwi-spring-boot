package com.desafio.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.models.Topic;
import com.desafio.springboot.repositories.TopicRepository;

@Service
public class TopicService {

  @Autowired
  TopicRepository topicRepository;

  public List<Topic> getTopics() {
    return topicRepository.findAll();
  }

  public void createTopic(Topic topic) {
    topicRepository.save(topic);
  }
}
