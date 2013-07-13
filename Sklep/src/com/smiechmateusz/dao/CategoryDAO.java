package com.smiechmateusz.dao;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Category;

@Transactional
public class CategoryDAO extends AbstractDAO
{
	public CategoryDAO()
	{
		super(Category.class);
	}
	
	public void delete(final Category entity) 
	{
		if (entity != null)
		{	
			ArrayList<Category> bar = (ArrayList<Category>) getAll();
			System.out.println("BAR SIZE:" + bar.size());
			for (int i = 0; i < bar.size(); i++)
			{
				System.out.println(bar.get(i).getId() + " " + bar.get(i).getName());
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
}
