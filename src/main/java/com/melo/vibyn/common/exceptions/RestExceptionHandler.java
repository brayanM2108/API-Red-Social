package com.melo.vibyn.common.exceptions;

import com.melo.vibyn.post.domain.exception.PostNotFoundException;
import com.melo.vibyn.spotify.domain.exception.SpotifyTokenRefreshException;
import com.melo.vibyn.user.domain.exception.EmailAlreadyExistsException;
import com.melo.vibyn.user.domain.exception.NicknameAlreadyExistsException;
import com.melo.vibyn.user.domain.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            NicknameAlreadyExistsException.class
    })
    public ResponseEntity<ErrorResponse> handleUniqueRestriccionException(HttpServletRequest request, Exception ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getClass().getSimpleName(),
                HttpStatus.CONFLICT.value(),
                new Timestamp(System.currentTimeMillis()),
                request.getRequestURI()
        );
        error.getErrors().put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler({
            PostNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest request, Exception ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getClass().getSimpleName(),
                HttpStatus.NOT_FOUND.value(),
                new Timestamp(System.currentTimeMillis()),
                request.getRequestURI()
        );
        error.getErrors().put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getClass().getSimpleName(),
                HttpStatus.BAD_REQUEST.value(),
                new Timestamp(System.currentTimeMillis()),
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(SpotifyTokenRefreshException.class)
    public ResponseEntity<ErrorResponse> handleSpotifyTokenRefreshException(HttpServletRequest request, SpotifyTokenRefreshException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getClass().getSimpleName(),
                HttpStatus.BAD_GATEWAY.value(),
                new Timestamp(System.currentTimeMillis()),
                request.getRequestURI()
        );
        error.getErrors().put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleUnknownException(HttpServletRequest request, Exception ex) {

        ErrorResponse error = new ErrorResponse(
                "UnknownError",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Timestamp(System.currentTimeMillis()),
                request.getRequestURI()
        );
        return ResponseEntity.internalServerError().body(error);
    }
}
