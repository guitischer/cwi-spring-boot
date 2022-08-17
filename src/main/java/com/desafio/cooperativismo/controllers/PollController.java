package com.desafio.cooperativismo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cooperativismo.dtos.PollDTO;
import com.desafio.cooperativismo.enums.ResultEnum;
import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.services.PollService;

@RestController
@RequestMapping("/polls")
public class PollController {

  @Autowired
  PollService pollService;

  @GetMapping
  public ResponseEntity<List<Poll>> list() {
    List<Poll> polls = pollService.getPolls();
    return ResponseEntity.ok(polls);
  }

  @PostMapping
  public ResponseEntity<Poll> create(@RequestBody PollDTO pollDTO) {
    pollService.createPoll(pollDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{pollId}/result")
  public ResponseEntity<ResultEnum> result(@PathVariable Long pollId) {
    ResultEnum result = pollService.getResult(pollId);
    return ResponseEntity.ok(result);
  }
}