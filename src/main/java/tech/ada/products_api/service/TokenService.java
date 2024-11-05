package tech.ada.products_api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.ada.products_api.exception.TokenInvalidException;
import tech.ada.products_api.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public static final String ISSUER = "product-api";
    @Value("${jwt-secret}")
    private String secret;

    public String generatedToken(User user) throws TokenInvalidException {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getLogin())
                    .withExpiresAt(getExpiresAt())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new TokenInvalidException();
        }

    }

    public String validateToken(String  token) throws TokenInvalidException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new TokenInvalidException();
        }
    }

    private Instant getExpiresAt() {
        return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"));
    }


}
