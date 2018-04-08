package com.swop4a.gd.chat.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Message {

	private MessageType type;
	private String content;
	private String sender;

	@Getter
	public enum MessageType {
		CHAT,
		JOIN,
		LEAVE;
	}
}
