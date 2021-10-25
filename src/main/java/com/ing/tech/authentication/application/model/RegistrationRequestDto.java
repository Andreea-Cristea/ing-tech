package com.ing.tech.authentication.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class RegistrationRequestDto {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String password;
}
