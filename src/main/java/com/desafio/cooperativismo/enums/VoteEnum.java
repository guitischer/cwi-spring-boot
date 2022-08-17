package com.desafio.cooperativismo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteEnum {
  YES(true),
  NO(false);

  private boolean decision;
}