package com.smiechmateusz.dao;

import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Role;

@Transactional
public class RoleDAO extends AbstractDAO
{
	public RoleDAO()
	{
		super(Role.class);
	}
}
