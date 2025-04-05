package com.learn.jwtrsa.controller;

import com.learn.jwtrsa.dto.WebResponse;
import com.learn.jwtrsa.dto.user.UserRegisterRequest;
import com.learn.jwtrsa.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class AuthController {

    private final AuthService authService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Void> register(@Valid @RequestBody UserRegisterRequest request){
        return authService.registerUser(request);
    }

}
