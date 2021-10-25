package com.ing.tech.authentication.application.controller;

import com.ing.tech.authentication.application.model.RegistrationRequestDto;
import com.ing.tech.authentication.application.service.RegistrationService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  @PostMapping(value= "registration")
  public ResponseEntity register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
    registrationService.registerUser(registrationRequestDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

}
