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

/**
 * Model for image, containing data like owner article and path.
 * 
 * @author Śmiech Mateusz
 */
@Entity
@Table(name="Image")
public class Image implements Serializable
{
	
	/** The Constant TYPE_MAIN. */
	public static final int TYPE_MAIN = 1;
	
	/** The Constant TYPE_ADDITIONAL. */
	public static final int TYPE_ADDITIONAL = 2;
	
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name="id")
	long id;
	
	/** The article. */
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="images")
	Article article;
	
	/** The path. */
	@Column(name="path")
	String path;
	
	/** The type. */
	@Column(name="type")
	int type;
	
	/**
	 * Instantiates a new image.
	 * 
	 * @param article the owner article
	 * @param path the file name path
	 * @param type the type
	 */
	public Image(Article article, String path, int type)
	{
		super();
		this.article = article;
		this.path = path;
		this.type = type;
	}
	
	/**
	 * Instantiates a new image.
	 */
	public Image()
	{
		
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
	 * Gets the owner article.
	 * 
	 * @return the article
	 */
	public Article getArticle()
	{
		return article;
	}
	
	/**
	 * Sets the article.
	 * 
	 * @param article the new owner article
	 */
	public void setArticle(Article article)
	{
		this.article = article;
	}
	
	/**
	 * Gets the file name path.
	 * 
	 * @return the file name path
	 */
	public String getPath()
	{
		return path;
	}
	
	/**
	 * Sets the path.
	 * 
	 * @param path the new file name path
	 */
	public void setPath(String path)
	{
		this.path = path;
	}
	
	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType()
	{
		return type;
	}
	
	/**
	 * Sets the type.
	 * 
	 * @param type the new type
	 */
	public void setType(int type)
	{
		this.type = type;
	}
}
