package com.learn.jwtrsa.service;

import com.learn.jwtrsa.dto.WebResponse;
import com.learn.jwtrsa.dto.user.UserRegisterRequest;
import com.learn.jwtrsa.entity.User;
import com.learn.jwtrsa.repository.UserRepository;
import com.learn.jwtrsa.utils.BCrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    public WebResponse<Void> registerUser(UserRegisterRequest request) {
        User register = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .build();

        userRepository.save(register);

        return WebResponse.<Void>builder()
                .message("Register successfully!")
                .build();
    }



}
