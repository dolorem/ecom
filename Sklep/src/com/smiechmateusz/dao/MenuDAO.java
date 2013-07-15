package com.smiechmateusz.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;

import com.smiechmateusz.model.Menu;

public class MenuDAO extends AbstractDAO
{
	public MenuDAO()
	{
		super(Menu.class);
	}
	
	public void delete(final Menu entity) 
	{
		if (entity != null)
		{	
			ArrayList<Menu> bar = (ArrayList<Menu>) getAll();
			for (int i = 0; i < bar.size(); i++)
			{
				if (bar.get(i).getParent() == entity)
				{
					bar.get(i).setParent(null);
					update(bar.get(i));
					System.out.println("UPDATED");
				}
			}
			this.getCurrentSession().delete(entity);
			System.out.println("DELETED SAFELY");
	       	this.getCurrentSession().flush();
	    }
	}
	
	public Criteria getCriteria()
	{
		return getCurrentSession().createCriteria(Menu.class);
	}
}
