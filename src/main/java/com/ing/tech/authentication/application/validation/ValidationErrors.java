package com.ing.tech.authentication.application.validation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationErrors {

  public static final String INVALID_EMAIL_MESSAGE ="The email provided is invalid. Please provide a valid email";
  public static final String EMPTY_FIRST_NAME_MESSAGE = "The first name field must not be empty";
  public static final String NULL_FIRST_NAME_MESSAGE = "The first name field must not be null";
  public static final String EMPTY_LAST_NAME_MESSAGE = "The last name field must not be empty";
  public static final String NULL_LAST_NAME_MESSAGE = "The last name field must not be null";
  public static final String NULL_PASSWORD_MESSAGE = "The password field must not be null";
  public static final String EMPTY_PASSWORD_MESSAGE = "The password field must not be empty";
}
