package com.ja.crud.infra.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ja.crud.model.CustomUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class JwtUtil {

    @Value("api.jwt.issuer")
    private String ISSUER;

    @Value("api.jwt.secret")
    private String SECRET;


    public String createToken(CustomUser customUser){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Instant expiredAt = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(2).toInstant();
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(Instant.now())
                .withExpiresAt(expiredAt)
                .withSubject(customUser.getUserName())
                .sign(algorithm);
    }

    public String subjectFromToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();

    }
}
