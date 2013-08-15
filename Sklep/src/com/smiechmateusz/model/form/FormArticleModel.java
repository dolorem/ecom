package com.smiechmateusz.model.form;

import java.util.Set;

public class FormArticleModel
{
	private String name;
	private String description;
	private boolean available;
	private long id;
	Set<Long> categories;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public boolean isAvailable()
	{
		return available;
	}
	public void setAvailable(boolean available)
	{
		this.available = available;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public void setCategories(Set<Long> categories)
	{
		this.categories = categories;
	}
	public Set<Long> getCategories()
	{
		return categories;
	}
}
