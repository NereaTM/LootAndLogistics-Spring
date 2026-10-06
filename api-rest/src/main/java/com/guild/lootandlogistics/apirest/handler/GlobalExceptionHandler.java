package com.guild.lootandlogistics.apirest.handler;

import com.guild.lootandlogistics.domain.exception.QuestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Convierte las excepciones del dominio en respuestas HTTP
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Datos que el dominio no acepta
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // El encargo no existe
    @ExceptionHandler(QuestNotFoundException.class)
    public ProblemDetail handleQuestNotFound(QuestNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}