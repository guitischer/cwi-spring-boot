package com.desafio.cooperativismo.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class UserDTO {

  @NotBlank
  private String name;

  @NotBlank
  @CPF
  private String cpf;

}
