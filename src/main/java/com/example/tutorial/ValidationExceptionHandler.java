package com.example.tutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    /**
     * 拦截Controller层的异常
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> exceptionHandler(Exception e, HttpServletRequest request){
        log.error("req: {}, err： {}", request.getRequestURI(), e.getMessage());
        // 注解验证抛出的异常
        if(e instanceof MethodArgumentNotValidException){
            StringBuilder builder = new StringBuilder();

            ((MethodArgumentNotValidException)e).getAllErrors().forEach(err -> builder.append(err.getDefaultMessage()).append(";"));
            return ResponseEntity.ok(ResponseMessage.error(404, builder.toString()));
        }

        return ResponseEntity.ok(ResponseMessage.error(404, e.getMessage()));
    }
}
