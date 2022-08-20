package com.desafio.cooperativismo.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "polls")
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Poll {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "topic_id", nullable = false)
  private Topic topic;

  @Column(name = "end_at")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
  private LocalDateTime endAt;

  @OneToMany(mappedBy = "poll", fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Vote> votes;

  @CreationTimestamp
  private LocalDateTime createdAt;
}