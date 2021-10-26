package com.ing.tech.authentication.application.service;

import com.ing.tech.authentication.application.model.RegistrationRequestDto;
import com.ing.tech.authentication.domain.cache.repository.UserRepository;
import com.ing.tech.authentication.domain.mapper.UserMapper;
import com.ing.tech.authentication.domain.model.User;
import com.ing.tech.authentication.domain.model.UserRole;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RegistrationService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;


  public void registerUser(RegistrationRequestDto registrationRequestDto) {
    final User user = userMapper.registrationRequestDtoToUser(registrationRequestDto);
    final String email = user.getEmail();
    if (userExists(email)) {
      throw new IllegalArgumentException("The email has already been taken");
    }
    populateUserWithSecurityDetails(user);
    userRepository.save(user);

  }

  private void populateUserWithSecurityDetails(User user) {
    List<UserRole> userRoles = new ArrayList<>();
    userRoles.add(UserRole.USER);
    user.setAppUserRole(userRoles);
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
  }

  private boolean userExists(String userEmail) {
    return userRepository.findByEmail(userEmail).isPresent();
  }
}
