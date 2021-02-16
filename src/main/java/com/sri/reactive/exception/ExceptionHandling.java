package com.sri.reactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(value = AnimeNotfoundException.class)
	public ResponseEntity<Object> exception(AnimeNotfoundException exception) {
		return new ResponseEntity<>("Anime not found", HttpStatus.NOT_FOUND);
	}
}
