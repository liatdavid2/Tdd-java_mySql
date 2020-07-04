package com.group.db.springbootmysql.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
