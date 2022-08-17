package com.desafio.cooperativismo.dtos;

import javax.validation.constraints.NotBlank;

import com.desafio.cooperativismo.enums.VoteEnum;
import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.models.User;

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
