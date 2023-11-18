package com.tsailin.springbootmall.service.Impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsailin.springbootmall.dao.UserDao;
import com.tsailin.springbootmall.dto.UserRegisterRequest;
import com.tsailin.springbootmall.model.User;
import com.tsailin.springbootmall.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}
	
	@Override
	public Integer register(@Valid UserRegisterRequest userRegisterRequest) {
		return userDao.register(userRegisterRequest);
	}

	
}
