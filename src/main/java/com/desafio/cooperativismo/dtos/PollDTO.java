package com.desafio.cooperativismo.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PollDTO {

  @NotBlank
  private Long topicId;

  @NotBlank
  private LocalDateTime endAt;
}
