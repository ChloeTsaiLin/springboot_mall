package com.tsailin.springbootmall.service;

import javax.validation.Valid;

import com.tsailin.springbootmall.dto.UserLoginRequest;
import com.tsailin.springbootmall.dto.UserRegisterRequest;
import com.tsailin.springbootmall.model.User;

public interface UserService {
	
	User getUserById(Integer userId);

	Integer register(@Valid UserRegisterRequest userRegisterRequest);

	User login(@Valid UserLoginRequest userLoginRequest);

}
