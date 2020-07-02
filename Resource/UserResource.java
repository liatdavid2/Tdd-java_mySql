package com.group.db.springbootmysql.Resource;

import java.util.List;
import java.util.Optional;

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
	
	//url: http://localhost:7000/rest/users/user?Id=3
	@GetMapping(value="/user")
	public Optional<User>  getById(@RequestParam int Id)
	{
		return UserRepository.findById(Id);
	}
	//url:http://localhost:7000/rest/users/add
		//body:{"name":"hfghf4324325","id":777888} - replace id- id is auto increment -don't take user id
	@PostMapping(value="/save")
	public int SaveUser(@RequestBody User _user)
	{
		System.out.println(_user.getID()+ _user.getName());
		UserRepository.save(_user);	
		return _user.getID();
	}
	//url:http://localhost:7000/rest/users/add
	//body:{"name":"hfghf4324325","id":777888} - replace id- id is auto increment -don't take user id
	@PostMapping(value="/add")
	public GenericResponse AddUser(@RequestBody User _user)
	{
		System.out.println(_user.getID()+ _user.getName());
		BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
		_user.setPassword(PasswordEncoder.encode(_user.getPassword()));
		UserRepository.save(_user);	
		return new GenericResponse("user saved");
	}
	//url:http://localhost:7000/rest/users/delete
	//body:    { "name": "hfghf7777", "id": 4}
	@DeleteMapping(value="/delete")
	public List<User>DeleteUser(@RequestBody User _user)
	{
		UserRepository.delete(_user);		
		return UserRepository.findAll();
	}
	//if user id doesn't exist's then create user - take user id
	//url:http://localhost:7000/rest/users/update
	//body:{"name":"2222222","id":2}
	@PutMapping(value="/update")
	public List<User>updateUser(@RequestBody User _user)
	{
		
		UserRepository.save(_user);		
		return UserRepository.findAll();
	}
}
