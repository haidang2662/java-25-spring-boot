package com.example.homeworkemail01.exception.handler;

import com.example.homeworkemail01.exception.EmailExistedException;
import com.example.homeworkemail01.exception.ObjectNotFoundException;
import com.example.homeworkemail01.exception.UnprocessableEntityException;
import com.example.homeworkemail01.model.response.CommonErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value
            = {UnprocessableEntityException.class, ObjectNotFoundException.class, EmailExistedException.class})
    protected ResponseEntity<CommonErrorModel> handleConflict(Exception ex) {
        CommonErrorModel response = CommonErrorModel.builder()
                .message(ex.getMessage())
                .build();

        if (ex instanceof UnprocessableEntityException) {
            response.setCode(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        } else if (ex instanceof ObjectNotFoundException) {
            response.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else if (ex instanceof EmailExistedException) {
            response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.internalServerError().body(response);
    }

}
