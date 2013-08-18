package com.smiechmateusz.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;

import com.smiechmateusz.model.Menu;

/**
 * AbstractDAO implementation for Menu, overriding some of its methods.
 * 
 * @author Śmiech Mateusz
 */
public class MenuDAO extends AbstractDAO
{
	
	/**
	 * Instantiates a new menu dao.
	 */
	public MenuDAO()
	{
		super(Menu.class);
	}
}
