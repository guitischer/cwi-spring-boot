package com.desafio.cooperativismo.responses;

import com.desafio.cooperativismo.enums.UserVoteEnum;

import lombok.Data;

@Data
public class CPFResponse {

  // Retorna se o usuário pode votar ou não, a partir do cpf do mesmo.
  private UserVoteEnum status;

}
