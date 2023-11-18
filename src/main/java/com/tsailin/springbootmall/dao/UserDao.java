package com.tsailin.springbootmall.dao;

import javax.validation.Valid;

import com.tsailin.springbootmall.dto.UserRegisterRequest;
import com.tsailin.springbootmall.model.User;

public interface UserDao {
	
	User getUserById(Integer userId);

	Integer register(@Valid UserRegisterRequest userRegisterRequest);

}
