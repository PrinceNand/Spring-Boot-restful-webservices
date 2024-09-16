package com.spring.springboot_restful_webservices.controller;


import com.spring.springboot_restful_webservices.dto.UserDto;
import com.spring.springboot_restful_webservices.entity.User;
import com.spring.springboot_restful_webservices.exception.ErrorDetails;
import com.spring.springboot_restful_webservices.exception.ResourcesNotFoundException;
import com.spring.springboot_restful_webservices.service.UserServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserServices userServices;

    // build create User REST API
    @PostMapping("create")
    public ResponseEntity<UserDto> createuser(@RequestBody @Valid UserDto user){
        UserDto savedUsed = userServices.createUser(user);
        return new ResponseEntity<>(savedUsed , HttpStatus.CREATED);
    }

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userServices.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    // Build Get All Users REST API
    @GetMapping("allUsers")
    public ResponseEntity<List<UserDto>> getUserById(){
        List<UserDto> user = userServices.getAllUsers();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    // Build Update User REST API
    @PutMapping("updateUser/{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUser = userServices.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    // Build Delete User REST API
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userID){
        userServices.deleteUserById(userID);
        return new ResponseEntity<>("User Successfully Deleted",HttpStatus.OK);
    }

//    Making it global exception
    /*@ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourcesNotFoundException exception,
                                                                        WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/
}
