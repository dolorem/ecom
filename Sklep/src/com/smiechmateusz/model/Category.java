package com.smiechmateusz.model;

import java.io.Serializable;
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

@Entity
@Table(name="Category")
public class Category implements Serializable
{
	@Id
	@GeneratedValue
	@Column(name="id")
	long id;
	/*@Column(name="parent")
	long parent;*/
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="parent")
	Category parent;
	@OneToMany(targetEntity=Category.class, mappedBy="parent", cascade=CascadeType.PERSIST)
	List<Category> children;
	@Column(name="name")
	String name;
	@ManyToMany(cascade=CascadeType.PERSIST, targetEntity=Article.class)
	@JoinTable(name="category_article", joinColumns = {@JoinColumn(name="categoryId", referencedColumnName="id")}, 
		inverseJoinColumns={@JoinColumn(name="articleId", referencedColumnName="id")})
	List<Article> articles;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	/*public long getParent()
	{
		return parent;
	}
	public void setParent(long parent)
	{
		this.parent = parent;
	}*/
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public List<Article> getArticles()
	{
		return articles;
	}
	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
	}
	public Category getParent()
	{
		return parent;
	}
	public void setParent(Category parent)
	{
		this.parent = parent;
	}
	public List<Category> getChildren()
	{
		return children;
	}
	public void setChildren(List<Category> children)
	{
		this.children = children;
	}
	
	
}
