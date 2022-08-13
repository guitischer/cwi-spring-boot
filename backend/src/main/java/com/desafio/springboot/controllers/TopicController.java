package com.desafio.springboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.springboot.dtos.TopicDTO;
import com.desafio.springboot.models.Topic;
import com.desafio.springboot.services.TopicService;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  TopicService topicService;

  @GetMapping
  public ResponseEntity<List<Topic>> list() {
    List<Topic> topics = topicService.getTopics();
    return ResponseEntity.ok(topics);
  }

  @PostMapping
  public ResponseEntity<Topic> create(@RequestBody @Valid TopicDTO topicDTO) {
    topicService.createTopic(topicDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
