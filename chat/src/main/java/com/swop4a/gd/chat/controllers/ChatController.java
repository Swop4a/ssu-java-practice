package com.swop4a.gd.chat.controllers;

import com.swop4a.gd.chat.dao.RoomsDao;
import com.swop4a.gd.chat.model.Message;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ChatController {

	//TODO make inject through YAML file
	private static final String USERNAME = "username";
	private static final String ROOM = "room";

	private final RoomsDao roomsDao;

	@Autowired
	public ChatController(RoomsDao roomsDao) {
		this.roomsDao = roomsDao;
	}

	@MessageMapping("/chat.sendMessage/{room}")
	@SendTo("/topic/rooms/{room}")
	public Message sendMessage(@Payload Message message) {
		return message;
	}

	@MessageMapping("/chat.connectUser")
	public void connectUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put(USERNAME, message.getSender());
	}

	@MessageMapping("/chat.joinRoom/{room}")
	@SendTo("/topic/rooms/{room}")
	public Message joinRoom(@DestinationVariable String room, @Payload Message message,
		SimpMessageHeaderAccessor headerAccessor) {
		List<String> rooms = roomsDao.findAll();
		if (!rooms.contains(room)) {
			roomsDao.insert(room);
		}
		headerAccessor.getSessionAttributes().put(ROOM, room);
		return message;
	}

	@MessageMapping("/chat.getRooms/")
	@SendTo("/topic/users/{username}")
	public List<String> getRooms() {
		return roomsDao.findAll();
	}
}
