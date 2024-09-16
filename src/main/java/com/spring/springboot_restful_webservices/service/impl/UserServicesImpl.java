package com.spring.springboot_restful_webservices.service.impl;

import com.spring.springboot_restful_webservices.dto.UserDto;
import com.spring.springboot_restful_webservices.entity.User;
import com.spring.springboot_restful_webservices.exception.EmailAlreadyExistsExceptions;
import com.spring.springboot_restful_webservices.exception.ResourcesNotFoundException;
import com.spring.springboot_restful_webservices.repository.UserRepository;
import com.spring.springboot_restful_webservices.service.UserServices;
import com.spring.springboot_restful_webservices.usermapper.AutoUserMapper;
import com.spring.springboot_restful_webservices.usermapper.UserMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsExceptions("Email Already Exists for User");
        }

        // Convert UserDto to User JPA entity
        /*User user1 = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );*/

        // By self coding
        // User user1 = UserMapper.mapToUser(userDto);

        // By using modelMapper
//        User user1 = modelMapper.map(userDto, User.class);

        // By using mapStructMapper
        User user1 = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user1);

        // Convert User JPA entity to UserDto
        /*UserDto savedUserDto = new UserDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );*/

        // By self coding
//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        // By using modelMapper
//        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);

        // By using mapStructMapper
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }


    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourcesNotFoundException("user", "id" , userId)
        );

        // By self coding
//        return UserMapper.mapToUserDto(user);

        // By using modelMapper
//        return modelMapper.map(user,UserDto.class);

        // By using mapStructMapper
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        // By self coding
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

        // By using modelMapper
//        return users.stream().map(user -> modelMapper.map(user,UserDto.class))
//                .collect(Collectors.toList());

        // By using mapStructMapper
        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new  ResourcesNotFoundException("user" , "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // By self coding
//        return UserMapper.mapToUserDto(updatedUser);

        // By using modelMapper
//        return modelMapper.map(updatedUser,UserDto.class);

        // By using mapStructMapper
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);

    }

    @Override
    public void deleteUserById(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new  ResourcesNotFoundException("user" , "id", userId)
        );

        userRepository.deleteById(userId);
    }
}