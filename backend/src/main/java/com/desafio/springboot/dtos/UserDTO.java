package com.desafio.springboot.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDTO {

  @NotBlank
  private String name;

  @NotBlank
  private String cpf;

}
