package com.smiechmateusz.model.form;

import java.util.LinkedList;
import java.util.Queue;

import com.smiechmateusz.dao.MenuDAO;
import com.smiechmateusz.model.Menu;

public class MenuFormModel extends Menu
{
	/** The parent id. */
	private long parentId;
	
	/**
	 * Instantiates a new Menu form model.
	 */
	public MenuFormModel()
	{
		super();
	}
	
	/**
	 * Instantiates a new Menu form model from a Menu.
	 * 
	 * @param c the Menu to copy fields from
	 */
	public MenuFormModel(Menu c)
	{
		super();
		this.id = c.getId();
		this.description = c.getDescription();
		this.link = c.getLink();
		this.parent = c.getParent();
		this.nestingLevel = c.getNestingLevel();
		this.priority = c.getPriority();
	}
	
	/**
	 * Parses the model and updates the Menu.
	 * 
	 * @param c the target Menu
	 * @param MenuDAO the Menu dao
	 */
	public void parseModel(Menu c, MenuDAO menuDAO)
	{
		c.setChildren(this.children);
		c.setDescription(this.description);
		c.setLink(this.link);
		c.setPriority(0);
		if (parentId == 0)
			c.setParent(null);
		else
		{
			if (c.getParent() != null)
			{
				c.getParent().removeChild(c);
				menuDAO.update(c.getParent());
			}
			Menu newParent = (Menu) menuDAO.getById(parentId);
			newParent.addChild(c);
			menuDAO.update(newParent);
			c.setParent(newParent);
		}
		menuDAO.update(c);
		System.out.println("updated");
	}

	/**
	 * Checks if Menu will be self child after update. This would cause Menu loops and is highly unwanted.
	 * 
	 * @param Menu the Menu to check
	 * @return true, if Menu will be self child after update, false otherwise
	 */
	public boolean gonnaBeSelfChild(Menu Menu)
	{
		if (Menu.getId() == parentId)
			return true;
		Queue<Menu> queue = new LinkedList<Menu>();
		queue.add(Menu);
		while (!queue.isEmpty())
		{
			for (Menu c : queue.poll().getChildren())
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
