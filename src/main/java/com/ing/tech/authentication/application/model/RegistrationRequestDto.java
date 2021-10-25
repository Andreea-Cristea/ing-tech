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
public class RegistrationRequestDto {
  @NotEmpty(message = ValidationErrors.EMPTY_FIRST_NAME_MESSAGE)
  @NotNull(message = ValidationErrors.NULL_FIRST_NAME_MESSAGE)
  private String firstName;
  @NotEmpty(message = ValidationErrors.EMPTY_LAST_NAME_MESSAGE)
  @NotNull(message = ValidationErrors.NULL_LAST_NAME_MESSAGE)
  private String lastName;
  @Email(message = ValidationErrors.INVALID_EMAIL_MESSAGE)
  private String email;
  @NotNull(message = ValidationErrors.NULL_PASSWORD_MESSAGE)
  @NotEmpty(message = ValidationErrors.EMPTY_PASSWORD_MESSAGE)
  private String password;
}
