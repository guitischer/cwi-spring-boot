package com.desafio.cooperativismo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cooperativismo.dtos.VoteDTO;
import com.desafio.cooperativismo.models.Vote;
import com.desafio.cooperativismo.services.VoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/votes")
@Api(value = "Endpoints de Votação")
public class VoteController {

  @Autowired
  VoteService voteService;

  @ApiOperation(value = "Criação do voto")
  @PostMapping
  public ResponseEntity<Vote> create(@RequestBody VoteDTO voteDTO) {
    voteService.createVote(voteDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
