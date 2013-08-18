package com.smiechmateusz.dao;

import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Role;

/**
 * AbstractDAO implementation form Role, overriding some of its methods.
 * 
 * @author Śmiech Mateusz
 */
@Transactional
public class RoleDAO extends AbstractDAO
{
	
	/**
	 * Instantiates a new role dao.
	 */
	public RoleDAO()
	{
		super(Role.class);
	}
}
