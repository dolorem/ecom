package com.smiechmateusz.model.form;

import java.util.Set;

public class FormArticleModel
{
	private String name;
	private String description;
	private boolean available;
	private long id;
	private Set<Long> categories;
	private Set<Long> deletedImagesId;
	private boolean deletedMainImage;
	private int amountOfAddedImages;
	
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
	public Set<Long> getDeletedImagesId()
	{
		return deletedImagesId;
	}
	public void setDeletedImagesId(Set<Long> deletedImagesId)
	{
		this.deletedImagesId = deletedImagesId;
	}
	public boolean isDeletedMainImage()
	{
		return deletedMainImage;
	}
	public void setDeletedMainImage(boolean deletedMainImage)
	{
		this.deletedMainImage = deletedMainImage;
	}
	public int getAmountOfAddedImages()
	{
		return amountOfAddedImages;
	}
	public void setAmountOfAddedImages(int amountOfAddedImages)
	{
		this.amountOfAddedImages = amountOfAddedImages;
	}
}
