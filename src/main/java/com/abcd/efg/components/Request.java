package com.abcd.efg.components;

public class Request {
	private String username;
	private String email;
	
	public Request (String username, String email)
	{
		this.username = username;
		this.email = email;
	}
	
	public String getUsername ()
	{
		return username;
	}
	
	public String getEmail()
	{
		return email;
	}
}
