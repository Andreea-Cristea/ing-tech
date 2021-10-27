package com.ing.tech.authentication.application.service;

import com.ing.tech.authentication.application.exceptions.AuthenticationBusinessException;
import com.ing.tech.authentication.application.model.LoginRequestDto;
import com.ing.tech.authentication.application.model.RegistrationRequestDto;
import com.ing.tech.authentication.domain.cache.repository.UserRepository;
import com.ing.tech.authentication.domain.mapper.UserLoginDetailsMapper;
import com.ing.tech.authentication.domain.mapper.UserMapper;
import com.ing.tech.authentication.domain.model.User;
import com.ing.tech.authentication.domain.model.UserLoginDetails;
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
public class UserProcessor {

  private final UserMapper userMapper;
  private final UserLoginDetailsMapper userLoginDetailsMapper;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;


  public void registerUser(RegistrationRequestDto registrationRequestDto) {
    final User user = userMapper.registrationRequestDtoToUser(registrationRequestDto);
    final String email = user.getEmail();
    if (userExists(email)) {
      throw new AuthenticationBusinessException("The email has already been taken");
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

  public String loginUser(LoginRequestDto loginRequestDto) {
    final UserLoginDetails userLoginDetails = userLoginDetailsMapper
        .loginRequestDtoToUserLoginDetails(loginRequestDto);
    final String userLoginEmail = userLoginDetails.getEmail();
    final String userLoginPassword = userLoginDetails.getPassword();
    if (!userExists(userLoginEmail)) {
      throw new AuthenticationBusinessException(String
          .format("The user with the following email %s does not exist",
              userLoginEmail));
    }
    final User user = userRepository.findByEmail(userLoginEmail).get();
    final String userFirstName = user.getFirstName();
    final String userEncodedPassword = user.getPassword();
    if (!bCryptPasswordEncoder.matches(userLoginPassword, userEncodedPassword)) {
      throw new AuthenticationBusinessException("The password for this user is wrong");
    }

    return userFirstName;
  }
}
