package com.tsailin.springbootmall.service.Impl;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.tsailin.springbootmall.dao.UserDao;
import com.tsailin.springbootmall.dto.UserLoginRequest;
import com.tsailin.springbootmall.dto.UserRegisterRequest;
import com.tsailin.springbootmall.model.User;
import com.tsailin.springbootmall.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}
	
	@Override
	public Integer register(@Valid UserRegisterRequest userRegisterRequest) {
		User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
		
		if(user != null) {
			log.warn("This email({}) is already registered", userRegisterRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return userDao.register(userRegisterRequest);
		
	}

	@Override
	public User login(@Valid UserLoginRequest userLoginRequest) {
		User user = userDao.getUserByEmail(userLoginRequest.getEmail());
		
		if(user == null) {
			log.warn("This email({}) is not registered", userLoginRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if(user.getUserPassword().equals(userLoginRequest.getPassword())){
			return user;
		}else {
			log.warn("This email({}) address or password is incorrect", userLoginRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	
}
