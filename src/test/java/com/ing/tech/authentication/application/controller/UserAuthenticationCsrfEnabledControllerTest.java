package com.ing.tech.authentication.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.tech.authentication.application.model.LoginRequestDto;
import com.ing.tech.authentication.application.model.RegistrationRequestDto;
import com.ing.tech.authentication.application.service.UserAuthenticationManager;
import com.ing.tech.authentication.application.service.UserProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(profiles = "test-complete-security")
@WebMvcTest(UserAuthenticationController.class)
class UserAuthenticationCsrfEnabledControllerTest {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private UserProcessor userProcessor;
  @MockBean
  private UserAuthenticationManager userAuthenticationManager;
  @MockBean
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Test
  void testRegisterSecurityEnabledForbiddenStatus() throws Exception {
    String registrationRequestString = objectMapper
        .writeValueAsString(createRegistrationRequestDto());
    mvc.perform(
        post("/api/v1/registration").contentType(MediaType.APPLICATION_JSON)
            .content(registrationRequestString)
    ).andExpect(status().isForbidden());
  }

  private RegistrationRequestDto createRegistrationRequestDto() {
    return new RegistrationRequestDto("Jane", "Doe",
        "janeDoe@gmail.com", "1234");
  }

  @Test
  void testLoginSecurityEnabled() throws Exception {
    String loginRequestDtoString = objectMapper
        .writeValueAsString(createLoginRequestDto());
    mvc.perform(
        get("/api/v1/login").contentType(MediaType.APPLICATION_JSON)
            .content(loginRequestDtoString)
    ).andExpect(status().isOk());
  }

  private LoginRequestDto createLoginRequestDto() {
    return new LoginRequestDto("janeDoe@gmail.com", "1234");
  }
}