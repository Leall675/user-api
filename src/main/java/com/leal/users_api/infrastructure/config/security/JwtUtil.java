package com.leal.users_api.infrastructure.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.leal.users_api.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt_secret_key}")
    private String SECRET_KEY;

    public String generateToken(User user) {
        try {
            log.info("Gerando token para o usuário com CPF: {}", user.getCpf());
            return JWT.create()
                    .withIssuer("users-api")
                    .withSubject(user.getCpf())
                    .withClaim("roles", user.getRoles().stream().map(Enum::name).toList())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(getAlgorithm());

        } catch (JWTCreationException exception) {
            log.error("Erro ao gerar token: {}", exception.getMessage());
            throw new RuntimeException("Erro ao se autenticar");
        }
    }

    public String validateToken(String token) {
        try {
            log.info("Iniciando a validação do Token.");
            String subject = JWT.require(getAlgorithm())
                    .withIssuer("users-api")
                    .build()
                    .verify(token)
                    .getSubject();
            log.info("Token validado, sujeito: {}", subject);
            return subject;
        } catch (JWTVerificationException exception) {
            log.error("Token inválido: {}", exception.getMessage());
            return null;
        }
    }

    public String extractUsername(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer("users-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            log.error("Token inválido: {}", exception.getMessage());
            throw new JWTVerificationException("Token inválido ou expirado");
        }
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY);
    }

    public Instant generateExpirationDate() {
        return Instant.now().plus(1, ChronoUnit.HOURS);
    }
}
