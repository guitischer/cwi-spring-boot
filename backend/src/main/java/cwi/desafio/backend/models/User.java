package cwi.desafio.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Table(name =  "users")
@Entity
@Data
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;
  @Column(name = "cpf", unique = true)
  private String cpf;
  @Column(name = "email", unique = true)
  private String email;


}
