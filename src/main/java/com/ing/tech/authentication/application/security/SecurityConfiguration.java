package com.ing.tech.authentication.application.security;

import com.ing.tech.authentication.application.service.UserAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserAuthenticationManager userAuthenticationManager;
  private final BCryptPasswordEncoder bCryptPAsswordEncoder;

  @Bean
  ServletRegistrationBean h2servletRegistration(){
    ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    return registrationBean;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/v1/registration")
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/login").permitAll()
        .and()
        .authorizeRequests().antMatchers("/console/**").permitAll()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(userAuthenticationManager)
        .passwordEncoder(bCryptPAsswordEncoder);
  }

}
