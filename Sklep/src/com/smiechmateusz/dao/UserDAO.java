package com.smiechmateusz.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.MyUser;

@Transactional
public class UserDAO extends AbstractDAO
{
	public UserDAO()
	{
		super(MyUser.class);
	}
	
	public MyUser searchDatabase(String username)
	{
		Criteria c = getCurrentSession().createCriteria(MyUser.class);
		c.add(Restrictions.like("username", username));
		List<MyUser> foo = c.list();
		if (foo.size() > 0)
			return foo.get(0);
		return null;
	}
}
