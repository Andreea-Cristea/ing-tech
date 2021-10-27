package com.ing.tech.authentication.application.model;

import com.ing.tech.authentication.application.validation.ValidationErrors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

  @Email
  @NotNull(message = ValidationErrors.NULL_EMAIL_MESSAGE)
  @NotEmpty(message = ValidationErrors.EMPTY_EMAIL_MESSAGE)
  private String email;
  @NotNull(message = ValidationErrors.NULL_PASSWORD_MESSAGE)
  @NotEmpty(message = ValidationErrors.EMPTY_PASSWORD_MESSAGE)
  private String password;

}
