package com.smiechmateusz.model;

import java.util.Date;

/**
 * Model for transaction containing data like article, user, amount and date.
 * 
 * @author Śmiech Mateusz
 */
public class Transaction 
{
	
	/** The id. */
	long id;
	
	/** The article. */
	Article article;
	
	/** The user. */
	MyUser user;
	
	/** The amount. */
	long amount;
	
	/** The course. */
	double course;
	
	/** The date. */
	Date date;
	
	/** The type. */
	int type;
	
	/**
	 * Instantiates a new transaction.
	 * 
	 * @param id the id
	 * @param article the article
	 * @param user the user
	 * @param amount the amount
	 * @param course the course
	 * @param date the date
	 * @param type the type
	 */
	public Transaction(long id, Article article, MyUser user, long amount,
			double course, Date date, int type)
	{
		super();
		this.id = id;
		this.article = article;
		this.user = user;
		this.amount = amount;
		this.course = course;
		this.date = date;
		this.type = type;
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
	 * Gets the article.
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
	 * @param article the new article
	 */
	public void setArticle(Article article)
	{
		this.article = article;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public MyUser getUser()
	{
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user the new user
	 */
	public void setUser(MyUser user)
	{
		this.user = user;
	}

	/**
	 * Gets the amount.
	 * 
	 * @return the amount
	 */
	public long getAmount()
	{
		return amount;
	}

	/**
	 * Sets the amount.
	 * 
	 * @param amount the new amount
	 */
	public void setAmount(long amount)
	{
		this.amount = amount;
	}

	/**
	 * Gets the course.
	 * 
	 * @return the course
	 */
	public double getCourse()
	{
		return course;
	}

	/**
	 * Sets the course.
	 * 
	 * @param course the new course
	 */
	public void setCourse(double course)
	{
		this.course = course;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the new date
	 */
	public void setDate(Date date)
	{
		this.date = date;
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
