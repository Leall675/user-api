package com.leal.users_api.infrastructure.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.leal.users_api.application.dto.error.ErroCampo;
import com.leal.users_api.application.dto.error.ErroResposta;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handleGenericException(Exception ex) {
        ErroResposta resposta = new ErroResposta();
        resposta.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resposta.setMessage("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        resposta.setErros(List.of());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErroResposta resposta = new ErroResposta();

        resposta.setStatus(HttpStatus.BAD_REQUEST.value());
        resposta.setMessage("Erro de leitura do JSON. Verifique os campos enviados.");
        String rawMessage = ex.getMostSpecificCause().getMessage();
        String field = rawMessage.contains("\"") ? rawMessage.split("\"")[1] : "json";
        resposta.setErros(List.of(
                new ErroCampo(field, "Campo não reconhecido no payload.")
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErroCampo> erro = ex.getFieldErrors()
                .stream()
                .map(fieldError -> new ErroCampo(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ErroResposta resposta = new ErroResposta();
        resposta.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        resposta.setMessage("Erro de validação.");
        resposta.setErros(erro);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resposta);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErroResposta> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        ErroResposta resposta = new ErroResposta();
        resposta.setStatus(HttpStatus.CONFLICT.value());
        resposta.setMessage(ex.getMessage());
        resposta.setErros(List.of());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }

}
