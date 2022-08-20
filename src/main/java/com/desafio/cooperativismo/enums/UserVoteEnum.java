package com.desafio.cooperativismo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserVoteEnum {
  ABLE_TO_VOTE("ABLE_TO_VOTE"),
  UNABLE_TO_VOTE("UNABLE_TO_VOTE");

  private String response;
}