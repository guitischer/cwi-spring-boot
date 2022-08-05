package com.desafio.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.springboot.models.Poll;
import com.desafio.springboot.services.PollService;

@RestController
@RequestMapping("/polls")
public class PollController {

  @Autowired
  PollService pollService;

  @PostMapping
  public ResponseEntity<Poll> create(@RequestBody Poll poll) {
    pollService.createPoll(poll);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
