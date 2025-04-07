package com.learn.jwtrsa.service;

import com.learn.jwtrsa.config.JwtProvider;
import com.learn.jwtrsa.dto.WebResponse;
import com.learn.jwtrsa.dto.user.UserLoginRequest;
import com.learn.jwtrsa.dto.user.UserRegisterRequest;
import com.learn.jwtrsa.entity.User;
import com.learn.jwtrsa.repository.UserRepository;
import com.learn.jwtrsa.utils.BCrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public WebResponse<String> loginUser(UserLoginRequest request) {
        User user = getUserByEmail(request.getEmail());

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        try{
            String token = JwtProvider.generateToken(user.getUsername());

            return WebResponse.<String>builder()
                    .message("login successfully")
                    .data(token)
                    .build();
        } catch (Exception e) {
            return WebResponse.<String>builder()
                    .message("login failed")
                    .build();
        }
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
