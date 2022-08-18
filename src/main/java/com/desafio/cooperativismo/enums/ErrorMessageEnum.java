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
  REQUIRED_TOPIC_FIELD("O campo Pauta é obrigatório"),

  USER_NOT_FOUND("O id do Usuário não foi encontrado"),
  VOTE_NOT_FOUND("O id do Voto não foi encontrado"),
  POLL_NOT_FOUND("O id da Sessão de Votação não foi encontrado"),
  TOPIC_NOT_FOUND("O id da Pauta não foi encontrado"),

  INVALID_CPF("CPF inválido"),
  UNABLE_TO_VOTE("Esse usuário já votou, portanto, não pode votar novamente");

  private String message;
}
