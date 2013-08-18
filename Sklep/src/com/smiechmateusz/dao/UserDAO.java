package com.smiechmateusz.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.MyUser;

/**
 * AbstractDAO implementation for User, overriding some of its methods.
 * 
 * @author Śmiech Mateusz
 */
@Transactional
public class UserDAO extends AbstractDAO
{
	
	/**
	 * Instantiates a new user dao.
	 */
	public UserDAO()
	{
		super(MyUser.class);
	}
	
	/**
	 * Find User by their username.
	 * 
	 * @param username the username of the user to find 
	 * @return the user with given username, null if they don't exist
	 */
	public MyUser findByUsername(String username)
	{
		Criteria c = getCurrentSession().createCriteria(MyUser.class);
		c.add(Restrictions.like("username", username));
		List<MyUser> foo = c.list();
		if (foo.size() > 0)
			return foo.get(0);
		return null;
	}
}
