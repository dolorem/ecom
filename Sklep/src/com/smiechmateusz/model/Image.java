package com.smiechmateusz.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Image")
public class Image implements Serializable
{
	@Id
	@GeneratedValue
	@Column(name="id")
	long id;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="images")
	Article article;
	@Column(name="path")
	String path;
	@Column(name="type")
	int type;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public Article getArticle()
	{
		return article;
	}
	public void setArticle(Article article)
	{
		this.article = article;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
}
