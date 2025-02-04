package com.spring.springboot_restful_webservices.usermapper;

import com.spring.springboot_restful_webservices.dto.UserDto;
import com.spring.springboot_restful_webservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
