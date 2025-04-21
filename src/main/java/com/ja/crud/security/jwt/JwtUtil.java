package com.ja.crud.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ja.crud.model.CustomAuthority;
import com.ja.crud.model.CustomUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${api.jwt.issuer}")
    private String ISSUER;

    @Value("${api.jwt.secret}")
    private String SECRET;

    @Value("${api.jwt.time_spam}")
    private Integer TIME_SPAM;


    public String createToken(CustomUser customUser){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Instant expiredAt = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(this.TIME_SPAM).toInstant();
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(Instant.now())
                .withExpiresAt(expiredAt)
                .withSubject(customUser.getUserName())
                .withClaim("roles", customUser.getAuthorities().stream().map(CustomAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    private DecodedJWT decodedJWT(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token);
    }
    public String subjectFromToken(String token){
        return this.decodedJWT(token).getSubject();
    }

    public void isValidToken(String token) {

    }
}
