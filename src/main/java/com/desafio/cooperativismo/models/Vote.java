package com.desafio.cooperativismo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.desafio.cooperativismo.enums.VoteEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "votes")
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Vote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "vote", nullable = false)
  private VoteEnum vote;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "poll_id", nullable = false)
  @JsonBackReference
  private Poll poll;

}
