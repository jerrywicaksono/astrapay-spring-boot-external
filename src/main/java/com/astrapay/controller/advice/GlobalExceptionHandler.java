package com.astrapay.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.astrapay.dto.GeneralResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleValidationError(MethodArgumentNotValidException ex){
		String errMessage = ex.getBindingResult().getFieldErrors().stream().map(err -> err.getDefaultMessage()).findFirst().orElse("Validasi gagal");
		return ResponseEntity.badRequest().body(new GeneralResponse(errMessage, null));
	}
}