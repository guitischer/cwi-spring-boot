package com.desafio.cooperativismo.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.desafio.cooperativismo.models.Topic;

import lombok.Data;

@Data
public class PollDTO {

  @NotBlank
  private Topic topic;

  @NotBlank
  private LocalDateTime endAt;
}
