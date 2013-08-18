package com.smiechmateusz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	long id;
	
	/** The description. */
	@Column(name = "description")
	String description;
	
	/** The link. */
	@Column(name = "link")
	String link;
	
	/** The parent. */
	@ManyToOne(cascade=CascadeType.PERSIST, targetEntity=Menu.class)
	@JoinColumn(name = "parent")
	Menu parent;
	
	/** The children. */
	@OneToMany(cascade=CascadeType.PERSIST, targetEntity=Menu.class, mappedBy="parent")
	List<Menu> children;
	
	/** The priority. */
	@Column(name="priority")
	int priority;
	
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
}
