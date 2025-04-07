package com.learn.jwtrsa.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.learn.jwtrsa.utils.RSAKeyUtil;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

public class JwtValidator {

    public static DecodedJWT verifyToken(String token) throws Exception{
        PublicKey publicKey = RSAKeyUtil.getPublicKey();

        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("jwt-rsa")
                .build();

        return verifier.verify(token);
    }

}
