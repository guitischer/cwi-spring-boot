package com.desafio.cooperativismo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserVoteEnum {
  ABLE_TO_VOTE("Able to vote"),
  UNABLE_TO_VOTE("Unable to vote");

  private String possibilityToVote;
}