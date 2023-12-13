package com.tsailin.springbootmall.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.tsailin.springbootmall.dao.UserDao;
import com.tsailin.springbootmall.dto.UserRegisterRequest;
import com.tsailin.springbootmall.model.User;
import com.tsailin.springbootmall.rowmapper.UserRowMapper;

@Component
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public User getUserById(Integer userId) {
		String sql = "SELECT * FROM user_table WHERE user_id = :userId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if(userList.size() > 0) {
			return userList.get(0);
		}else {			
			return null;
		}
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "SELECT * FROM user_table WHERE email = :email";
		
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if(userList.size() > 0) {
			return userList.get(0);
		}else {			
			return null;
		}
	}

	@Override
	public Integer register(@Valid UserRegisterRequest userRegisterRequest) {
		String sql = "INSERT INTO user_table (email, user_password, created_date, last_modified_date)"
				+ "VALUES (:email, :userPassword, :createdDate, :lastModifiedDate)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("email", userRegisterRequest.getEmail());
		map.put("userPassword", userRegisterRequest.getPassword());
		
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();		
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
		int userId = keyHolder.getKey().intValue();
		
		return userId;
	}
	
}
