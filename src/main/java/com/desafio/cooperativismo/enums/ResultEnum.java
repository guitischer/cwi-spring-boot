package com.desafio.cooperativismo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
  APPROVED("Aprovado"),
  DISAPPROVED("Reprovado"),
  TIE("Empate");

  private String result;
}
