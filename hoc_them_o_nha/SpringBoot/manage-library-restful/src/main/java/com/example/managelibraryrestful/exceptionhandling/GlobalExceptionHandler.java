package com.example.managelibraryrestful.exceptionhandling;


import com.example.managelibraryrestful.exceptionhandling.exception.InvalidBookBorrowQuantityException;
import com.example.managelibraryrestful.exceptionhandling.exception.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ResponseStatus(HttpStatus.NOT_FOUND)
////    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
////    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ExceptionHandler(ObjectNotFoundException.class)
//    public String handleValidationExceptions(ObjectNotFoundException ex, Model model) {
//        model.addAttribute("errorMess", ex.getMessage());
//        return "/404";
////        Map<String, String> errors = new HashMap<>();
////        ex.getBindingResult().getAllErrors().forEach((error) -> {
////            String fieldName = ((FieldError) error).getField();
////            String errorMessage = error.getDefaultMessage();
////            errors.put(fieldName, errorMessage);
////        });
////        return errors;
//    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
//    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(ObjectNotFoundException ex) {
//        return ResponseEntity.notFound().build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidBookBorrowQuantityException.class)
    public ResponseEntity<?> handleInvalidBookBorrowException(InvalidBookBorrowQuantityException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
    }


}
