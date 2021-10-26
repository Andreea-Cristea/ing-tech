package com.ing.tech.authentication.domain.mapper;

import com.ing.tech.authentication.application.model.LoginRequestDto;
import com.ing.tech.authentication.domain.model.UserLoginDetails;
import org.mapstruct.Mapper;

@Mapper
public interface UserLoginDetailsMapper {
  LoginRequestDto userLoginDetailsToLoginRequestDto(UserLoginDetails user);
  UserLoginDetails loginRequestDtoToUserLoginDetails(LoginRequestDto loginRequestDto);
}
