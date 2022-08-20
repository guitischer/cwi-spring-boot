package com.desafio.cooperativismo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PollStatusEnum {
  POLL_OPENED("Sessão de votação em andamento"),
  POLL_CLOSED("Sessão de votação finalizada");

  private String response;
}