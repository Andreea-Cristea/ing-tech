package com.ing.tech.authentication.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDetails {

  private String email;
  private String password;
}
