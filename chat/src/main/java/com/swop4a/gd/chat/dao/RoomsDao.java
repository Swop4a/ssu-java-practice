package com.swop4a.gd.chat.dao;

import java.util.List;

public interface RoomsDao {

	List<String> findAll();

	void insert(String room);
}
