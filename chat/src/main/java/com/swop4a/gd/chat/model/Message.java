package com.swop4a.gd.chat.model;

public class Message {

	private MessageType type;
	private String content;
	private String sender;

	public enum MessageType {
		CHAT,
		JOIN,
		LEAVE;
	}
}
