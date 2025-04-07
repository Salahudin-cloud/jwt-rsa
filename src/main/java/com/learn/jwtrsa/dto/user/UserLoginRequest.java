package com.learn.jwtrsa.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserLoginRequest {

    @NotEmpty(message = "Email are required")
    private String email;

    @NotEmpty(message = "Password are required")
    private String password;

}
