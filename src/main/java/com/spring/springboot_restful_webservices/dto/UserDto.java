package com.spring.springboot_restful_webservices.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private Long id;

    @NotEmpty(message = "Enter First Name Mandatory")
    private String firstName;

    @NotEmpty(message = "Enter Last Name Mandatory")
    private String lastName;

    @NotEmpty(message = "Enter Email Mandatory")
    @Email(message = "Enter Valid Email address Mandatory")
    private String email;

}
