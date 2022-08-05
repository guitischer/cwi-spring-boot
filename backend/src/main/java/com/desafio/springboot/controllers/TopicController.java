package com.desafio.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.springboot.models.Topic;
import com.desafio.springboot.services.TopicService;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  TopicService topicService;

  @PostMapping
  public ResponseEntity<Topic> create(@RequestBody Topic topic) {
    topicService.createTopic(topic);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
