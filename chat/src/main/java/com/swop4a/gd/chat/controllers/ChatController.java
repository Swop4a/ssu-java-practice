package com.swop4a.gd.chat.controllers;

import com.swop4a.gd.chat.model.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	private static final String SENDER_NAME_KEY = "username";

	@MessageMapping("/chat.sendMessage/{room}")
	@SendTo("/topic/public")
	public Message sendMessage(@DestinationVariable String room, @Payload Message message) {
		return message;
	}

	@MessageMapping("/chat.addUser/{room}")
	@SendTo("/topic/public")
	public Message addUser(@DestinationVariable String room, @Payload Message message,
		SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put(SENDER_NAME_KEY, message.getSender());
		return message;
	}
}
