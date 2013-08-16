package com.smiechmateusz.model;

import java.util.LinkedList;
import java.util.Queue;

import com.smiechmateusz.dao.CategoryDAO;

public class CategoryFormModel extends Category
{
	private long parentId;
	
	public CategoryFormModel()
	{
		
	}
	
	public CategoryFormModel(Category c)
	{
		this.id = c.getId();
		this.articles = c.getArticles();
		this.children = c.getChildren();
		this.name = c.getName();
		this.parent = c.getParent();
	}
	
	public void parseModel(Category c, CategoryDAO categoryDAO)
	{
		c.setName(this.name);
		if (parentId == 0)
			c.setParent(null);
		else
		{
			if (c.getParent() != null)
			{
				c.getParent().removeChild(c);
				categoryDAO.update(c.getParent());
			}
			Category newParent = (Category) categoryDAO.getById(parentId);
			newParent.addChild(c);
			categoryDAO.update(newParent);
			c.setParent(newParent);
		}
		categoryDAO.update(c);
	}

	public boolean gonnaBeSelfChild(Category category)
	{
		if (category.getId() == parentId)
			return true;
		Queue<Category> q = new LinkedList<Category>();
		q.add(category);
		while (!q.isEmpty())
		{
			for (Category c : q.poll().getChildren())
			{
				if (c.getId() == parentId)
					return true;
				q.add(c);
			}
		}
		return false;
	}
	
	public long getParentId()
	{
		return parentId;
	}

	public void setParentId(long parentId)
	{
		this.parentId = parentId;
	}
	
	
}
