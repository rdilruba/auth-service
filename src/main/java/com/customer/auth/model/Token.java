package com.customer.auth.model;

public class Token {

	String token;
	String refToken;
	String username;
	
	public Token(String token, String refToken, String username) {
		this.token = token;
		this.refToken = refToken;
		this.username = username;
	}
	
	public String getToken() {
		return token;
	}

	public String getRefToken() {
		return refToken;
	}

	public String getUsername() {
		return username;
	}
}
