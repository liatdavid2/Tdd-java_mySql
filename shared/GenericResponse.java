package com.group.db.springbootmysql.shared;

import lombok.Data;
@Data
public class GenericResponse {
	
	private String Message;
	
	
	
	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenericResponse(String message) {
		Message = message;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
}
