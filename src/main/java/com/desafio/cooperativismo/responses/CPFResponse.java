package com.desafio.cooperativismo.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CPFResponse {

  // Retorna se o usuário pode votar ou não, a partir do cpf do mesmo.
  private String status;

}
