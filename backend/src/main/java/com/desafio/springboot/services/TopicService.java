package com.desafio.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.models.Topic;
import com.desafio.springboot.repositories.TopicRepository;

@Service
public class TopicService {
  
  @Autowired
  TopicRepository topicRepository;

  public void createTopic(Topic topic){
    topicRepository.save(topic);
  }
}
