package com.smiechmateusz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * Model for Menu containing data like link, submenus or text.
 * 
 * @author Śmiech Mateusz
 */
@Entity(name = "Menu")
public class Menu implements Serializable
{
	
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "menu")
	protected long id;
	
	/** The description. */
	@Column(name = "description")
	protected String description;
	
	/** The link. */
	@Column(name = "link")
	protected String link;
	
	/** The parent. */
	@ManyToOne(cascade=CascadeType.PERSIST, targetEntity=Menu.class)
	@JoinColumn(name = "parent")
	protected Menu parent;
	
	/** The children. */
	@OneToMany(cascade=CascadeType.PERSIST, targetEntity=Menu.class, mappedBy="parent")
	protected List<Menu> children;
	
	/** The priority. */
	@Column(name="priority")
	protected int priority;
	
	/** The nesting level. */
	@Transient
	protected int nestingLevel;
	
	/**
	 * Instantiates a new menu.
	 */
	public Menu()
	{
		this.children = new ArrayList<Menu>();
	}
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}
	
	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Gets the link.
	 * 
	 * @return the link
	 */
	public String getLink()
	{
		return link;
	}
	
	/**
	 * Sets the link.
	 * 
	 * @param link the new link
	 */
	public void setLink(String link)
	{
		this.link = link;
	}
	
	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Menu getParent()
	{
		return parent;
	}
	
	/**
	 * Sets the parent.
	 * 
	 * @param parent the new parent
	 */
	public void setParent(Menu parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List<Menu> getChildren()
	{
		return children;
	}
	
	/**
	 * Sets the children.
	 * 
	 * @param children the new children
	 */
	public void setChildren(List<Menu> children)
	{
		this.children = children;
	}
	
	/**
	 * Gets the priority.
	 * 
	 * @return the priority
	 */
	public int getPriority()
	{
		return priority;
	}
	
	/**
	 * Sets the priority.
	 * 
	 * @param priority the new priority
	 */
	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	/**
	 * Gets the nesting level.
	 * 
	 * @return the nesting level
	 */
	public int getNestingLevel()
	{
		return nestingLevel;
	}

	/**
	 * Sets the nesting level.
	 * 
	 * @param nestingLevel
	 *            the new nesting level
	 */
	public void setNestingLevel(int nestingLevel)
	{
		this.nestingLevel = nestingLevel;
	}

	/**
	 * Adds the child.
	 * 
	 * @param c
	 *            the child to add
	 */
	public void addChild(Menu c)
	{
		children.add(c);		
	}

	/**
	 * Gets the parent as array list.
	 * 
	 * @return the parent as array list
	 */
	public ArrayList<Menu> getParentAsArrayList()
	{
		ArrayList<Menu> list = new ArrayList<Menu>();
		list.add(this.parent);
		return list;
	}
	
	/**
	 * Removes the child.
	 * 
	 * @param c
	 *            the child to add
	 */
	public void removeChild(Menu c)
	{
		for (int i = 0; i < children.size(); i++)
		{
			if (children.get(i) == c)
			{
				children.remove(i);
				i--;
			}
		}
	}
}
