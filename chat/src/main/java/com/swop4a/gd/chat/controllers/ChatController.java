package com.swop4a.gd.chat.controllers;

import com.swop4a.gd.chat.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@MessageMapping
	@SendTo
	public Message sendMessage(@Payload Message message) {
		return message;
	}

	@MessageMapping
	@SendTo
	public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
		return message;
	}
}
