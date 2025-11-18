package com.fatec.projeto.controllers.advices;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fatec.projeto.dtos.response.ErrorDto;
import com.fatec.projeto.errors.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(
        MethodArgumentNotValidException ex,
        HttpServletRequest request
    ) {
        LOG.error("Validation error: {}", ex.getMessage());

        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList();

        ErrorDto errorDto = new ErrorDto(
            "Erro de validação",
            String.join("; ", errors),
            HttpStatus.BAD_REQUEST.value(),
            request.getServletPath(),
            LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(
        NotFoundException ex,
        HttpServletRequest request
    ) {
        LOG.error("Not Found error: {}", ex.getMessage());

        ErrorDto errorDto = new ErrorDto(
            "Não encontrado",
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            request.getServletPath(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(
        ConstraintViolationException ex,
        HttpServletRequest request
    ) {
        LOG.error("Constraint violation error: {}", ex.getMessage());
        List<String> errors = ex.getConstraintViolations()
            .stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .toList();
            
        ErrorDto errorDto = new ErrorDto(
            "Erro de validação",
            String.join("; ", errors),
            HttpStatus.BAD_REQUEST.value(),
            request.getServletPath(),
            LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(
        Exception ex,
        HttpServletRequest request
    ) {
        LOG.error("Internal server error: {}", ex.getMessage());

        ErrorDto errorDto = new ErrorDto(
            "Erro interno do servidor",
            ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            request.getServletPath(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}
