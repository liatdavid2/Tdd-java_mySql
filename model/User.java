package com.group.db.springbootmysql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
@Id	
@GeneratedValue
@Column(name="id")
private int ID;
@Column(name="name")
private String Name;

@Column(name="password")
private String Password;



public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}

}
