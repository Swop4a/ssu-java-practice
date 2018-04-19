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

	//TODO make inject through YAML file
	private static final String USERNAME = "params";
	private static final String ROOM = "room";

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

		String params = (String) headerAccessor.getSessionAttributes().get(USERNAME);
		if (params != null) {
			log.info("USER DISCONNECTED: {}", params);

			Message chatMessage = new Message();
			chatMessage.setType(Message.MessageType.LEAVE);
			chatMessage.setSender(params);

			String room = (String) headerAccessor.getSessionAttributes().get(ROOM);
			messagingTemplate.convertAndSend(String.format("/topic/rooms/%s", room), chatMessage);
		}
	}
}
