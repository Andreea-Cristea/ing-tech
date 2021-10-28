package com.ing.tech.authentication.application.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestInfoController {

  @GetMapping("/info")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public String greeting() {
    return "info";
  }

}
