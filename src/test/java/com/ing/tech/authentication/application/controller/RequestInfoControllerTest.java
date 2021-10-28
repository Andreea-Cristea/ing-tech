package com.ing.tech.authentication.application.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles(profiles = "test-basic-security")
@WebMvcTest(RequestInfoController.class)
class RequestInfoControllerTest {

  public static final String URI_INFO = "/info";
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private UserProcessor userProcessor;
  @MockBean
  private UserAuthenticationManager userAuthenticationManager;
  @MockBean
  private BCryptPasswordEncoder bCryptPasswordEncoder;


  @Test
  void testUserHavingValidAuthorityRequestsInfo() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/info").with(user("admin").roles("USER"))
        .accept(MediaType.ALL))
        .andExpect(status().isOk());
  }

  @Test
  void testUserHavingInvalidAuthorityRequestsInfo() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/info").with(user("admin").roles("HR"))
        .accept(MediaType.ALL))
        .andExpect(status().isForbidden());
  }

}