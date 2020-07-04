package com.group.db.springbootmysql.error;

import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {

	private long timestamp = new Date().getTime();
	
	private int status;
	
	private String url;
	
	private String message;

	private Map<String,String> validationErrors;

	
	
	public ApiError(int status, String url, String message) {
		super();
		this.status = status;
		this.url = url;
		this.message = message;
	}

	public ApiError() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, String> getValidationErrors() {
		return validationErrors;
	}
	public void setValidationErrors(Map<String, String> validationErrors) {
		this.validationErrors = validationErrors;
	}

	
	
	
}
