package com.pragyan.authapp.authapp.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 4, message = "User name must be minimum of 4 characters")
    private String name;
    @Email(message = "Your email address is not valid!")
    private String email;
    @NotEmpty
    @Size(min = 4, max = 10, message = "password must be must be min of 4 characters and max of 10 characters")
    private String password;
    @NotEmpty
    private String about;
}
