package com.learn.jwtrsa.dto.user;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterRequest {

    @NotEmpty(message = "Username are required")
    private String username;

    @NotEmpty(message = "Email are required")
    private String email;

    @NotEmpty(message = "Password are required")
    private String password;
}
