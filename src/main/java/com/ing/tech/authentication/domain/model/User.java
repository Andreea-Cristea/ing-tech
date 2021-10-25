package com.ing.tech.authentication.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
  @Id
  private long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
}
