package com.smiechmateusz.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
	
	/**
	 * Retrieves Menu items without parent as an alphabetically ordered list.
	 * 
	 * @return alphabetically ordered list of Menu items having no parent
	 */
	public ArrayList<Menu> loadRootAlphabetically()
	{
		Criteria c = this.getCurrentSession().createCriteria(Menu.class);
		c.add(Restrictions.isNull("parent"));
		c.addOrder(Order.asc("description"));
		return (ArrayList<Menu>) c.list();
	}
	
	/**
	 * Retrieves Menu items sorted appropriately with their children before their 
	 * siblings. This method also sets nesting level of each element.
	 * 
	 * @return the array list of sorted items
	 */
	public ArrayList<Menu> getItemOffsetAlphabeticalList()
	{
		ArrayList<Menu> root = loadRootAlphabetically();
		ArrayList<Menu> output = new ArrayList<Menu>();
		Stack<Menu> stack = new Stack<Menu>();
		for (int i = root.size() - 1; i >= 0; i--)
		{
			root.get(i).setNestingLevel(0);
			stack.add(root.get(i));
		}
		while (!stack.empty())
		{
			Menu item = stack.pop();
			List<Menu> children = item.getChildren();
			if (children != null)
			{
				for (int i = children.size() - 1; i >= 0; i--)
				{
					children.get(i).setNestingLevel(item.getNestingLevel() + 1);
					stack.add(children.get(i));
				}
			}
			output.add(item);
		}
		return output;
	}
	
	/**
	 * Deletes the target Menu item and all its children.
	 * 
	 * @param entity Menu item to delete
	 */
	public void delete(final Menu entity) 
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
				List<Menu> children = entity.getChildren();
				while (children.size() != 0)
					this.delete(children.get(0));
			}				
			this.getCurrentSession().delete(entity);
			this.getCurrentSession().flush();
	    }
	}

	/**
	 * Deletes target menu item by its ID.
	 * 
	 * @param id ID of item to delete
	 */
	public void deleteById(final long id)
	{
		this.delete((Menu) getById(id));
	}
}
