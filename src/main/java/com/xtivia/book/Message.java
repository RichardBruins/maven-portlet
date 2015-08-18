package com.xtivia.book;

import java.io.Serializable;

public class Message implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String welcomeMessage;
	
	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	public Message() {
	}
}
