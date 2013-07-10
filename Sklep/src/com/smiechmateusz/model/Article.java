package com.smiechmateusz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Article")
public class Article implements Serializable
{
	@Id
	@GeneratedValue()
	@Column(name="id")
	long id;
	@Column(name="images")
	@OneToMany(targetEntity=Image.class, mappedBy="article", cascade=CascadeType.ALL)
	List<Image> images;
	@Column(name="description")
	String description;
	@Transient
	//@Column(name="categories")
	List<Category> categories;
	@Column(name="addDate")
	Date addDate;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public List<Image> getImages()
	{
		return images;
	}
	public void setImages(List<Image> images)
	{
		this.images = images;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public List<Category> getCategories()
	{
		return categories;
	}
	public void setCategories(List<Category> categories)
	{
		this.categories = categories;
	}
	public Date getAddDate()
	{
		return addDate;
	}
	public void setAddDate(Date addDate)
	{
		this.addDate = addDate;
	}
}
