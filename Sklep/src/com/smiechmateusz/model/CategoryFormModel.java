package com.smiechmateusz.model;

import java.util.LinkedList;
import java.util.Queue;

import com.smiechmateusz.dao.CategoryDAO;

/**
 * Category model for use in forms. Contains both original and target data.
 * 
 * @author Śmiech Mateusz
 */
public class CategoryFormModel extends Category
{
	
	/** The parent id. */
	private long parentId;
	
	/**
	 * Instantiates a new category form model.
	 */
	public CategoryFormModel()
	{
		
	}
	
	/**
	 * Instantiates a new category form model from a category.
	 * 
	 * @param c the category to copy fields from
	 */
	public CategoryFormModel(Category c)
	{
		this.id = c.getId();
		this.articles = c.getArticles();
		this.children = c.getChildren();
		this.name = c.getName();
		this.parent = c.getParent();
	}
	
	/**
	 * Parses the model and updates the category.
	 * 
	 * @param c the target category
	 * @param categoryDAO the category dao
	 */
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

	/**
	 * Checks if category will be self child after update. This would cause category loops and is highly unwanted.
	 * 
	 * @param category the category to check
	 * @return true, if category will be self child after update, false otherwise
	 */
	public boolean gonnaBeSelfChild(Category category)
	{
		if (category.getId() == parentId)
			return true;
		Queue<Category> queue = new LinkedList<Category>();
		queue.add(category);
		while (!queue.isEmpty())
		{
			for (Category c : queue.poll().getChildren())
			{
				if (c.getId() == parentId)
					return true;
				queue.add(c);
			}
		}
		return false;
	}
	
	/**
	 * Gets the parent id.
	 * 
	 * @return the parent id
	 */
	public long getParentId()
	{
		return parentId;
	}

	/**
	 * Sets the parent id.
	 * 
	 * @param parentId the new parent id
	 */
	public void setParentId(long parentId)
	{
		this.parentId = parentId;
	}	
}
