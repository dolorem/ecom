package com.smiechmateusz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model for Manufacturer containing data like their name, description and a list of articles.
 * 
 * @author Śmiech Mateusz
 */

@Entity
@Table(name="Manufacturer")
public class Manufacturer implements Serializable
{
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name="id")
	protected long id;
	
	/** The name. */
	@Column(name="name")
	protected String name;
	
	/** The description. */
	@Column(name="description", columnDefinition="LONGTEXT")
	protected String description;
	
	/** The articles. */
	@OneToMany(targetEntity=Article.class, mappedBy="manufacturer", cascade=CascadeType.PERSIST)
	@Column(name="articles")
	protected List<Article> articles;
	
	/**
	 * 
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		if (!(other instanceof Manufacturer))
			return false;
		return ((Manufacturer) other).getId() == this.id;
	}
	
	/**
	 * Removes the article from list.
	 * 
	 * @param toRemove article to remove
	 */
	public void removeArticle(Article toRemove)
	{
		for (int i = 0; i < articles.size(); i++)
		{
			if (articles.get(i) == toRemove)
				articles.remove(i);
		}
	}
	
	/**
	 * Instantiates new manufacturer with non-null values.
	 */
	public Manufacturer()
	{
		this.name = "";
		this.description = "";
		this.articles = new ArrayList<Article>();
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
	 * @param id the id
	 */
	public void setId(long id)
	{
		this.id = id;
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
	 * @param name the name
	 */
	public void setName(String name)
	{
		this.name = name;
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
	 * Gets the articles.
	 * 
	 * @return the articles
	 */
	public List<Article> getArticles()
	{
		return articles;
	}

	/** 
	 * Sets the articles.
	 * 
	 * @param articles the articles
	 */
	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
	}
}
