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
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="Article")
public class Article implements Serializable
{
	@Id
	@GeneratedValue()
	@Column(name="id")
	long id;
	@Column(name="images")
	@OneToMany(targetEntity=Image.class, mappedBy="article", cascade=CascadeType.PERSIST)
	List<Image> images;
	@Column(name="description", columnDefinition="LONGTEXT")
	String description;
	@ManyToMany(cascade=CascadeType.PERSIST, targetEntity=Category.class)
	@JoinTable(name="category_article", joinColumns = {@JoinColumn(name="articleId", referencedColumnName="id")}, 
		inverseJoinColumns={@JoinColumn(name="categoryId", referencedColumnName="id")})
	List<Category> categories;
	@Column(name="addDate")
	Date addDate;
	@Column(name="name")
	String name;
	@Column(name="available")
	boolean available;
	
	public Article()
	{
		this.images = new ArrayList<Image>();
		this.description = "";
		this.categories = new ArrayList<Category>();
		this.addDate = new Date();
		this.name = "";
		this.available = false;
	}
	
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
	
	public void addImages(List<Image> newImages)
	{
		for (int i = 0; i < newImages.size(); i++)
			images.add(newImages.get(i));
	}
	
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
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available = available;
	}
	
	
	
	
	
	
	@Transient
	private Set<Long> categoriesId;
	@Transient
	private Set<Long> deletedImagesId;
	@Transient
	private boolean deletedMainImage;
	@Transient
	private int amountOfAddedImages;
	@Transient
	private CommonsMultipartFile newMainImage;
	@Transient
	private List<CommonsMultipartFile> newAdditionalImages;

	public Set<Long> getCategoriesId()
	{
		return categoriesId;
	}

	public void setCategoriesId(Set<Long> categoriesId)
	{
		this.categoriesId = categoriesId;
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

	public CommonsMultipartFile getNewMainImage()
	{
		return newMainImage;
	}

	public void setNewMainImage(CommonsMultipartFile newMainImage)
	{
		this.newMainImage = newMainImage;
	}

	public List<CommonsMultipartFile> getNewAdditionalImages()
	{
		return newAdditionalImages;
	}

	public void setNewAdditionalImages(
			List<CommonsMultipartFile> newAdditionalImages)
	{
		this.newAdditionalImages = newAdditionalImages;
	}
}
