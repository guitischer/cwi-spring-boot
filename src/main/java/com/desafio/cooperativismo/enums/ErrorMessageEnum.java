package com.desafio.cooperativismo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessageEnum {
  REQUIRED_NAME_FIELD("O campo NOME é obrigatório"),
  REQUIRED_CPF_FIELD("O campo CPF é obrigatório"),
  REQUIRED_VOTE_FIELD("O campo Voto é obrigatório"),
  REQUIRED_USER_FIELD("O campo Usuário é obrigatório"),
  REQUIRED_POLL_FIELD("O campo Sessão de Votação é obrigatório"),
  REQUIRED_TOPIC_FIELD("O campo Pauta é obrigatório");

  private String message;
}
