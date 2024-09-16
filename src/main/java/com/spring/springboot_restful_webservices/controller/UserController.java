package com.spring.springboot_restful_webservices.controller;


import com.spring.springboot_restful_webservices.dto.UserDto;
import com.spring.springboot_restful_webservices.entity.User;
import com.spring.springboot_restful_webservices.exception.ErrorDetails;
import com.spring.springboot_restful_webservices.exception.ResourcesNotFoundException;
import com.spring.springboot_restful_webservices.service.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserServices userServices;

    // build create User REST API
    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("create")
    public ResponseEntity<UserDto> createuser(@RequestBody @Valid UserDto user){
        UserDto savedUsed = userServices.createUser(user);
        return new ResponseEntity<>(savedUsed , HttpStatus.CREATED);
    }

    // build get user by id REST API
    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userServices.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    // Build Get All Users REST API
    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get a all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("allUsers")
    public ResponseEntity<List<UserDto>> getUserById(){
        List<UserDto> user = userServices.getAllUsers();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    // Build Update User REST API
    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("updateUser/{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUser = userServices.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    // Build Delete User REST API
    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
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
