package com.group.db.springbootmysql.Resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.db.springbootmysql.model.User;
import com.group.db.springbootmysql.repository.userRepository;
import com.group.db.springbootmysql.shared.GenericResponse;

@RestController
@RequestMapping(value="/rest/1.0/users")
public class UserResource {

	@Autowired
	userRepository UserRepository;
	//@Autowired
	//BCryptPasswordEncoder PasswordEncoder;
	//url:http://localhost:7000/rest/users/all
	@GetMapping(value="/all")
	public List<User>getAll()
	{
		return UserRepository.findAll();
	}	
	//url:http://localhost:7000/rest/users/add
	//body:{"name":"hfghf4324325","id":777888} - replace id- id is auto increment -don't take user id
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
