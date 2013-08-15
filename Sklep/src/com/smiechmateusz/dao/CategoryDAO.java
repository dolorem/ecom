package com.smiechmateusz.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Category;
import com.smiechmateusz.utils.Pair;

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
			for (int i = 0; i < bar.size(); i++)
			{
				if (bar.get(i).getParent() == entity)
				{
					bar.get(i).setParent(null);
					update(bar.get(i));
				}
			}
			this.getCurrentSession().delete(entity);
			this.getCurrentSession().flush();
	    }
	}
	
	public ArrayList<Category> loadRootAlphabetically()
	{
		Criteria c = this.getCurrentSession().createCriteria(Category.class);
		c.add(Restrictions.isNull("parent"));
		c.addOrder(Order.asc("name"));
		return (ArrayList<Category>) c.list();
	}
	
	public ArrayList<Pair<Category, Integer>> getItemOffsetAlphabeticalList()
	{
		ArrayList<Category> categories = loadRootAlphabetically();
		ArrayList<Pair<Category, Integer>> cat = new ArrayList<Pair<Category, Integer>>();
		Stack<Pair<Category, Integer>> q = new Stack<Pair<Category, Integer>>();
		for (int i = categories.size() - 1; i >= 0; i--)
			q.add(new Pair<Category, Integer>(categories.get(i), 0));
		while (!q.empty())
		{
			Pair<Category, Integer> pair = q.pop();
			List<Category> children = pair.getLeft().getChildrenAlphabetically();
			if (children != null)
			{
				for (int i = children.size() - 1; i >= 0; i--)
					q.add(new Pair<Category, Integer>(children.get(i), pair.getRight() + 1));
				cat.add(pair);
			}
		}
		return cat;
	}
}
