package com.desafio.springboot.dtos;

import javax.validation.constraints.NotBlank;

import com.desafio.springboot.models.Poll;
import com.desafio.springboot.models.User;

import lombok.Data;

@Data
public class VoteDTO {

  @NotBlank
  private Boolean vote;

  @NotBlank
  private User user;

  @NotBlank
  private Poll poll;
}
