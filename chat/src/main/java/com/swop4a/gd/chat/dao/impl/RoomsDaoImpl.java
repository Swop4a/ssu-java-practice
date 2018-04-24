package com.swop4a.gd.chat.dao.impl;

import com.swop4a.gd.chat.dao.RoomsDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RoomsDaoImpl implements RoomsDao {

	private static final String SELECT_ALL_QUERY = "SELECT room FROM rooms";
	private static final String INSERT_ROOM_QUERY = "INSERT INTO rooms(room) values(?)";

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public RoomsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<String> findAll() {
		return jdbcTemplate.queryForList(SELECT_ALL_QUERY, String.class);
	}

	@Override
	public void insert(String room) {
		jdbcTemplate.update(INSERT_ROOM_QUERY, room);
	}
}
