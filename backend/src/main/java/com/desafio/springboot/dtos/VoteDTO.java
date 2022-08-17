package com.desafio.springboot.dtos;

import javax.validation.constraints.NotBlank;

import com.desafio.springboot.enums.VoteEnum;
import com.desafio.springboot.models.Poll;
import com.desafio.springboot.models.User;

import lombok.Data;

@Data
public class VoteDTO {

  @NotBlank
  private VoteEnum vote;

  @NotBlank
  private User user;

  @NotBlank
  private Poll poll;
}
