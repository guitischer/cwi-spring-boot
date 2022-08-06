package com.desafio.springboot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "votes")
@Data
@Entity
public class Vote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "vote", nullable = false)
  private Boolean vote;

  @OneToMany
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany
  @JoinColumn(name = "poll_id", nullable = false)
  private Poll poll;

}
