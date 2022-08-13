package com.desafio.springboot.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.desafio.springboot.models.Topic;

import lombok.Data;

@Data
public class PollDTO {

  @NotBlank
  private Topic topic;

  @NotBlank
  private LocalDateTime endAt;
}
