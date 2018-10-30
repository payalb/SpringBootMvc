package com.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exception.DatabaseException;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(DatabaseException.class)
	public String handleDatabaseException(Model model, Exception e) {
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String handleSQLException(Model model, Exception e) {
		model.addAttribute("errorMsg", e.getMessage());
		e.printStackTrace();
		return "error";
	}
}
