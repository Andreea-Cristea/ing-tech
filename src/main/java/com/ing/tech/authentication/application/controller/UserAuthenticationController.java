package com.ing.tech.authentication.application.controller;

import com.ing.tech.authentication.application.model.LoginRequestDto;
import com.ing.tech.authentication.application.model.RegistrationRequestDto;
import com.ing.tech.authentication.application.service.UserOrganizer;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class UserAuthenticationController {

  private final UserOrganizer userOrganizer;

  @PostMapping(value = "registration")
  public ResponseEntity<RegistrationRequestDto> register(
      @Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
    userOrganizer.registerUser(registrationRequestDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @GetMapping(value = "login")
  public ResponseEntity<LoginRequestDto> login(
      @Valid @RequestBody LoginRequestDto loginRequestDto) {
    final String firstName = userOrganizer.loginUser(loginRequestDto);
    final String welcomeMessage = "Welcome " + firstName;
    return new ResponseEntity(welcomeMessage, HttpStatus.OK);
  }

}
