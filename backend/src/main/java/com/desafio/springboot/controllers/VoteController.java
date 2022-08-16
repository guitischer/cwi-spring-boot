package com.desafio.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.springboot.dtos.VoteDTO;
import com.desafio.springboot.models.Vote;
import com.desafio.springboot.services.VoteService;

@RestController
@RequestMapping("/votes")
public class VoteController {

  @Autowired
  VoteService voteService;

  @PostMapping
  public ResponseEntity<Vote> create(@RequestBody VoteDTO voteDTO) {
    voteService.createVote(voteDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
