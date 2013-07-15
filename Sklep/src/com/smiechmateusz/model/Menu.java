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

@Entity(name = "Menu")
public class Menu implements Serializable
{
	@Id
	@GeneratedValue
	@Column(name = "menu")
	long id;
	@Column(name = "description")
	String description;
	@Column(name = "link")
	String link;
	@ManyToOne(cascade=CascadeType.PERSIST, targetEntity=Menu.class)
	@JoinColumn(name = "parent")
	Menu parent;
	@OneToMany(cascade=CascadeType.PERSIST, targetEntity=Menu.class, mappedBy="parent")
	List<Menu> children;
	@Column(name="priority")
	int priority;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getLink()
	{
		return link;
	}
	public void setLink(String link)
	{
		this.link = link;
	}
	public Menu getParent()
	{
		return parent;
	}
	public void setParent(Menu parent)
	{
		this.parent = parent;
	}
	public List<Menu> getChildren()
	{
		return children;
	}
	public void setChildren(List<Menu> children)
	{
		this.children = children;
	}
	public int getPriority()
	{
		return priority;
	}
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
}
