package com.ing.tech.authentication.application.controller;

import com.ing.tech.authentication.application.model.LoginRequestDto;
import com.ing.tech.authentication.application.model.RegistrationRequestDto;
import com.ing.tech.authentication.application.service.UserProcessor;
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

  private final UserProcessor userProcessor;

  @PostMapping(value = "registration")
  public ResponseEntity<RegistrationRequestDto> register(
      @Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
    userProcessor.registerUser(registrationRequestDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @GetMapping(value = "login")
  public ResponseEntity<LoginRequestDto> login(
      @Valid @RequestBody LoginRequestDto loginRequestDto) {
    final String firstName = userProcessor.loginUser(loginRequestDto);
    final String welcomeMessage = String.format("Welcome %s", firstName) ;
    return new ResponseEntity(welcomeMessage, HttpStatus.OK);
  }

}
