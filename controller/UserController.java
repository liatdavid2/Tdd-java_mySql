package com.group.db.springbootmysql.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.group.db.springbootmysql.error.ApiError;
import com.group.db.springbootmysql.model.User;
import com.group.db.springbootmysql.repository.userRepository;
import com.group.db.springbootmysql.shared.GenericResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/rest/1.0/users")
public class UserController {

	@Autowired
	userRepository UserRepository;
	
	@GetMapping(value="/all")
	public List<User>getAll()
	{
		return UserRepository.findAll();
	}	

	@PostMapping(value="/add")
	public GenericResponse AddUser(@Valid @RequestBody User _user)
	{
		System.out.println(_user.getID()+ _user.getName());
		BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
		_user.setPassword(PasswordEncoder.encode(_user.getPassword()));
		UserRepository.save(_user);	
		return new GenericResponse("user saved");
	}
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		ApiError apiError = new ApiError(400, request.getServletPath(), "validation Error");
		
		BindingResult result = exception.getBindingResult();
		
		Map<String,String> validationErrors = new HashMap<>();
		for (FieldError fieldError: result.getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			
		}
		apiError.setValidationErrors(validationErrors);
		return apiError;
	}

}
