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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Category model containing data like subcategories and articles.
 * 
 * @author Śmiech Mateusz
 */
@Entity
@Table(name="Category")
public class Category implements Serializable
{
	
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name="id")
	long id;
	
	/** The parent. */
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="parent")
	Category parent;
	
	/** The children. */
	@OneToMany(targetEntity=Category.class, mappedBy="parent", cascade=CascadeType.PERSIST)
	List<Category> children;
	
	/** The name. */
	@Column(name="name")
	String name;
	
	/** The articles. */
	@ManyToMany(cascade=CascadeType.PERSIST, targetEntity=Article.class)
	@JoinTable(name="category_article", joinColumns = {@JoinColumn(name="categoryId", referencedColumnName="id")}, 
		inverseJoinColumns={@JoinColumn(name="articleId", referencedColumnName="id")})
	List<Article> articles;
	
	/** The nesting level. */
	@Transient
	private int nestingLevel;
	
	/**
	 * Returns parent category as an ArrayList containing one or zero elements.
	 * 
	 * @return ArrayList containing sole parent category
	 */
	public ArrayList<Category> getParentAsArrayList()
	{
		ArrayList<Category> list = new ArrayList<Category>();
		if (parent == null)
			return list;
		list.add(parent);
		return list;
	}
	
	/**
	 * Instantiates a new category.
	 */
	public Category()
	{
		this.children = new ArrayList<Category>();
		this.articles = new ArrayList<Article>();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other)
	{
		if (!(other instanceof Category))
			return false;
		return ((Category) other).getId() == this.id;
	}
	
	/**
	 * Gets the children ordered alphabetically.
	 * 
	 * @return the children ordered alphabetically
	 */
	public List<Category> getChildrenAlphabetically()
	{
		return getChildren();
	}
	
	/**
	 * Removes the child.
	 * 
	 * @param c the child to remove
	 */
	public void removeChild(Category c)
	{
		for (int i = 0; i < children.size(); i++)
		{
			if (children.get(i) == c)
				children.remove(i);
		}
	}
	
	/**
	 * Adds the child.
	 * 
	 * @param c the child to add
	 */
	public void addChild(Category c)
	{
		children.add(c);
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
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
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
	 * @param articles the new articles
	 */
	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
	}
	
	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Category getParent()
	{
		return parent;
	}
	
	/**
	 * Sets the parent.
	 * 
	 * @param parent the new parent
	 */
	public void setParent(Category parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List<Category> getChildren()
	{
		return children;
	}
	
	/**
	 * Sets the children.
	 * 
	 * @param children the new children
	 */
	public void setChildren(List<Category> children)
	{
		this.children = children;
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
	 * @param nestingLevel the nesting level
	 */
	public void setNestingLevel(int nestingLevel)
	{
		this.nestingLevel = nestingLevel;
	}	
}
