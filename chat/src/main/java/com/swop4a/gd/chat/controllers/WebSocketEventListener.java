package com.swop4a.gd.chat.controllers;

import com.swop4a.gd.chat.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
public class WebSocketEventListener {

	private static final String SENDER_NAME_KEY = "username";

	private final SimpMessageSendingOperations messagingTemplate;

	@Autowired
	public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		log.info("RECEIVED A NEW CONNECTION");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String username = (String) headerAccessor.getSessionAttributes().get(SENDER_NAME_KEY);
		if (username != null) {
			log.info("USER DISCONNECTED: {}", username);

			Message chatMessage = new Message();
			chatMessage.setType(Message.MessageType.LEAVE);
			chatMessage.setSender(username);

			messagingTemplate.convertAndSend("/topic/public", chatMessage);
		}
	}
}
