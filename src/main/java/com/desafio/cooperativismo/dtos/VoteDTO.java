package com.desafio.cooperativismo.dtos;

import javax.validation.constraints.NotBlank;

import com.desafio.cooperativismo.enums.VoteEnum;

import lombok.Data;

@Data
public class VoteDTO {

  @NotBlank
  private VoteEnum vote;

  @NotBlank
  private Long userId;

  @NotBlank
  private Long pollId;
}
