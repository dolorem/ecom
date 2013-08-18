package com.smiechmateusz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Article model containing both data visible only by user and backend information.
 * 
 * @author Śmiech Mateusz
 */
@Entity
@Table(name="Article")
public class Article implements Serializable
{
	
	/** The id. */
	@Id
	@GeneratedValue()
	@Column(name="id")
	long id;
	
	/** The images. */
	@Column(name="images")
	@OneToMany(targetEntity=Image.class, mappedBy="article", cascade=CascadeType.PERSIST)
	List<Image> images;
	
	/** The description. */
	@Column(name="description", columnDefinition="LONGTEXT")
	String description;
	
	/** The categories. */
	@ManyToMany(cascade=CascadeType.PERSIST, targetEntity=Category.class)
	@JoinTable(name="category_article", joinColumns = {@JoinColumn(name="articleId", referencedColumnName="id")}, 
		inverseJoinColumns={@JoinColumn(name="categoryId", referencedColumnName="id")})
	List<Category> categories;
	
	/** The add date. */
	@Column(name="addDate")
	Date addDate;
	
	/** The name. */
	@Column(name="name")
	String name;
	
	/** The available. */
	@Column(name="available")
	boolean available;
	
	/**
	 * Instantiates a new article with non-null values.
	 */
	public Article()
	{
		this.images = new ArrayList<Image>();
		this.description = "";
		this.categories = new ArrayList<Category>();
		this.addDate = new Date();
		this.name = "";
		this.available = false;
	}
	
	/**
	 * Gets the main image.
	 * 
	 * @return the main image
	 */
	public Image getMainImage()
	{
		Image img = null;
		for (Image i : images)
		{
			if (i.getType() == Image.TYPE_MAIN)
			{
				img = i;
				break;
			}
		}
		return img;
	}
	
	/**
	 * Gets the additional (non-main) images.
	 * 
	 * @return the additional images
	 */
	public List<Image> getAdditionalImages()
	{
		List<Image> img = new ArrayList<Image>();
		Image main = getMainImage();
		for (Image i : images)
		{
			if (i != main)
				img.add(i);
		}
		return img;
	}
	
	/**
	 * Adds the images.
	 * 
	 * @param newImages
	 *            the new images
	 */
	public void addImages(List<Image> newImages)
	{
		for (int i = 0; i < newImages.size(); i++)
			images.add(newImages.get(i));
	}
	
	/**
	 * Unbinds images by their IDs.
	 * 
	 * @param ids the IDs of images to unbind
	 */
	public void deleteImagesById(Set<Long> ids)
	{
		for (Long i : ids)
		{
			for (int j = 0; j < images.size(); j++)
			{
				if (images.get(j).getId() == i)
					images.remove(j);
			}
		}
	}
	
	/**
	 * Adds the image.
	 * 
	 * @param i the image to add
	 */
	public void addImage(Image i)
	{
		this.images.add(i);
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
	 * @param id
	 *            the new id
	 */
	public void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * Gets the images.
	 * 
	 * @return the images
	 */
	public List<Image> getImages()
	{
		return images;
	}
	
	/**
	 * Sets the images.
	 * 
	 * @param images
	 *            the new images
	 */
	public void setImages(List<Image> images)
	{
		this.images = images;
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
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Gets the categories.
	 * 
	 * @return the categories
	 */
	public List<Category> getCategories()
	{
		return categories;
	}
	
	/**
	 * Sets the categories.
	 * 
	 * @param categories
	 *            the new categories
	 */
	public void setCategories(List<Category> categories)
	{
		this.categories = categories;
	}
	
	/**
	 * Gets the adds the date.
	 * 
	 * @return the adds the date
	 */
	public Date getAddDate()
	{
		return addDate;
	}
	
	/**
	 * Sets the adds the date.
	 * 
	 * @param addDate
	 *            the new adds the date
	 */
	public void setAddDate(Date addDate)
	{
		this.addDate = addDate;
	}
	
	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Checks if is available.
	 * 
	 * @return true, if is available
	 */
	public boolean isAvailable()
	{
		return available;
	}

	/**
	 * Sets the available.
	 * 
	 * @param available
	 *            the new available
	 */
	public void setAvailable(boolean available)
	{
		this.available = available;
	}
}
