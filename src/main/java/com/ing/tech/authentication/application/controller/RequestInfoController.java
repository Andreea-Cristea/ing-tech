package com.ing.tech.authentication.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestInfoController {

  @RequestMapping("/info")
  public String greeting(Model model) {
    return "info";
  }

}
