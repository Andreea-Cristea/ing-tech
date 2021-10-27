package com.ing.tech.authentication.application.service;

import com.ing.tech.authentication.domain.cache.repository.UserRepository;
import com.ing.tech.authentication.domain.model.User;
import com.ing.tech.authentication.domain.model.UserRole;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthenticationManager implements UserDetailsService {

  public static final String USERNAME_NOT_FOUND_MESSAGE = "The username having the following email, %s, not found";
  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   Optional<User> userOptional = userRepository.findByEmail(email);
   if(!userOptional.isPresent()) {
     throw new UsernameNotFoundException(String.format(
         USERNAME_NOT_FOUND_MESSAGE, email));
   }
    User user = userOptional.get();
    org.springframework.security.core.userdetails.User preConfiguredSpringUser;

    List<UserRole> userRoles = user.getAppUserRole();
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (UserRole userRole : userRoles) {
      grantedAuthorities.add(new SimpleGrantedAuthority(userRole.toString()));
    }

    preConfiguredSpringUser = new org.springframework.security.core.userdetails.User(
        email,
        user.getPassword(),
        grantedAuthorities);

    return preConfiguredSpringUser;
  }
}
