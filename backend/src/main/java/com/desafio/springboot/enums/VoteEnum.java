package com.desafio.springboot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteEnum {
  YES(true),
  NOT(false);

  private boolean decision;
}