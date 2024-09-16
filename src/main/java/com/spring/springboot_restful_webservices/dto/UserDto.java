package com.spring.springboot_restful_webservices.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    @Schema(
            description = "User Id"
    )
    private Long id;

    @Schema(

            description = "User First Name"
    )
    @NotEmpty(message = "Enter First Name Mandatory")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "Enter Last Name Mandatory")
    private String lastName;

    @Schema(
            description = "User Email Address"
    )
    @NotEmpty(message = "Enter Email Mandatory")
    @Email(message = "Enter Valid Email address Mandatory")
    private String email;

}
