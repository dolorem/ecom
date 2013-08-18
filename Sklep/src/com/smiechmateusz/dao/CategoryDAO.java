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

/**
 * AbstractDAO implementation for Category overriding some of its methods.
 */
@Transactional
public class CategoryDAO extends AbstractDAO
{
	
	/**
	 * Instantiates a new category dao.
	 */
	public CategoryDAO()
	{
		super(Category.class);
	}
	
	/**
	 * Deletes the Category and all its subcategories.
	 * 
	 * @param entity the Category to delete
	 */
	public void delete(final Category entity) 
	{
		long id = entity.getId();
		if (entity != null)
		{	
			if (entity.getParent() != null) //first we have to detach the item from its parent
			{
				entity.getParent().removeChild(entity);
				update(entity.getParent());
			}
			if (entity.getChildren() != null) //we have to recursively delete every child before removing the entity
			{
				List<Category> children = entity.getChildren();
				while (children.size() != 0)
					this.delete(children.get(0));
			}				
			this.getCurrentSession().delete(entity);
			this.getCurrentSession().flush();
	    }
	}
	
	/**
	 * Deletes the category of given ID.
	 * 
	 * @param id the id of the Category to delete
	 */
	public void deleteById(final long id)
	{
		this.delete((Category) getById(id));
	}
	
	/**
	 * Loads alphabetically ordered categories with root (null) parent.
	 * 
	 * @return the alphabetically ordered list of categories with null parent 
	 */
	public ArrayList<Category> loadRootAlphabetically()
	{
		Criteria c = this.getCurrentSession().createCriteria(Category.class);
		c.add(Restrictions.isNull("parent"));
		c.addOrder(Order.asc("name"));
		return (ArrayList<Category>) c.list();
	}
	
	/**
	 * Creates the list of categories with their indent level. 
	 * The categories in each level are ordered alphabetically and children precede siblings.
	 * 
	 * @return the category list with offset
	 */
	public ArrayList<Category> getItemOffsetAlphabeticalList()
	{
		ArrayList<Category> root = loadRootAlphabetically();
		ArrayList<Category> output = new ArrayList<Category>();
		Stack<Category> stack = new Stack<Category>();
		for (int i = root.size() - 1; i >= 0; i--)
		{
			root.get(i).setNestingLevel(0);
			stack.add(root.get(i));
		}
		while (!stack.empty())
		{
			Category item = stack.pop();
			List<Category> children = item.getChildrenAlphabetically();
			if (children != null)
			{
				for (int i = children.size() - 1; i >= 0; i--)
				{
					children.get(i).setNestingLevel(item.getNestingLevel() + 1);
					stack.add(children.get(i));
				}
				output.add(item);
			}
		}
		return output;
	}
}
