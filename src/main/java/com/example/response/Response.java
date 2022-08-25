package com.example.response;

import org.springframework.stereotype.Component;

@Component
public class Response {
	
	String id;
	String message;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message, String action) {
		if (action.equalsIgnoreCase("create"))
			this.message = "Created the document for " + message;
		else if(action.equalsIgnoreCase("Update"))
			this.message = "Updated the document for " +  message;
		else
			this.message = message;
	}
	public Response(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	public Response() {
		super();
	}
	
	

}
