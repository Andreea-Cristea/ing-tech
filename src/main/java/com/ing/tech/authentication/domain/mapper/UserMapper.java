package com.ing.tech.authentication.domain.mapper;

import com.ing.tech.authentication.application.model.RegistrationRequestDto;
import com.ing.tech.authentication.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
  RegistrationRequestDto userToRegistrationRequestDto(User user);
  User registrationRequestDtoToUser(RegistrationRequestDto user);
}
