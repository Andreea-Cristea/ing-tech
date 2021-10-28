package com.ing.tech.authentication.application.security;

import com.ing.tech.authentication.application.service.UserAuthenticationManager;
import java.io.IOException;
import javax.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Profile({"prod", "test-complete-security"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityProductionConfig extends WebSecurityConfigurerAdapter {
  private final UserAuthenticationManager userAuthenticationManager;
  private final BCryptPasswordEncoder bCryptPAsswordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/api/v1/registration")
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/login").permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/home")
        .permitAll()
        .and()
        .logout(logout -> logout.logoutUrl("/api/v1/logout")
            .addLogoutHandler((request, response, auth) -> {
              try {
                request.logout();
                response.sendRedirect("/home");
              } catch (ServletException | IOException e) {
                log.error(e.getMessage());
              }
            })
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID"))
        .authorizeRequests()
        .anyRequest()
        .authenticated().and()
        .formLogin();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(userAuthenticationManager)
        .passwordEncoder(bCryptPAsswordEncoder);
  }
}
