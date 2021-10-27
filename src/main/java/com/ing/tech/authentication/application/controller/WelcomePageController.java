package com.ing.tech.authentication.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomePageController {

  @RequestMapping("/home")
  public String greeting() {
    return "home";
  }

}
