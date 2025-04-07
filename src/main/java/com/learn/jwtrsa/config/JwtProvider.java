package com.learn.jwtrsa.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.learn.jwtrsa.utils.RSAKeyUtil;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

public class JwtProvider {

    public static String generateToken(String username) throws Exception{
        PrivateKey privateKey = RSAKeyUtil.getPrivateKey();

        return JWT.create()
                .withSubject(username)
                .withIssuer("jwt-rsa")
                .withExpiresAt(new Date(System.currentTimeMillis() + 60_000))
                .sign(Algorithm.RSA256(null, ((RSAPrivateKey) privateKey)));
    }

}
