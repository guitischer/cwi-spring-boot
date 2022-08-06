package com.desafio.springboot.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "users")
@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "cpf", unique = true, nullable = false)
  private String cpf;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @OneToMany(mappedBy = "user")
  private List<Vote> votes;

}
