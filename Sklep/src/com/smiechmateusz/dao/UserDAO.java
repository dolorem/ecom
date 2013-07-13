package com.smiechmateusz.dao;

import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.User;

@Transactional
public class UserDAO extends AbstractDAO
{
	public UserDAO()
	{
		super(User.class);
	}
}
