package com.smiechmateusz.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Manufacturer;

/**
 * AbstractDAO implementation for Manufacturer, overriding some of its methods.
 * 
 * @author Śmiech Mateusz
 */
public class ManufacturerDAO extends AbstractDAO
{
	/** The article dao. */
	@Autowired
	ArticleDAO articleDAO;
	
	/**
	 * Instantiates a new manufacturer dao.
	 */
	public ManufacturerDAO()
	{
		super(Manufacturer.class);
	}
	
	/**
	 * Retrieves all manufacturers orderer alphabetically.
	 * 
	 * @return list of manufacturers ordered alphabetically
	 */
	public List<Manufacturer> getAllAlphabeticall()
	{
		Criteria c = this.getCurrentSession().createCriteria(Manufacturer.class);
		c.addOrder(Order.asc("name"));
		return c.list();
	}
	
	/**
	 * Retrieves manufacturer by their id.
	 * 
	 * @param id the id of manufacturer to retrieve
	 * @return the manufacturer of given id
	 */
	public Manufacturer getById(long id)
	{
		return (Manufacturer) super.getById(id);
	}
	
	/**
	 * Deletes the manufacturer of given id.
	 * 
	 * @param id the id of manufacturer to delete
	 */
	public void deleteById(long id)
	{
		Manufacturer m = getById(id);
		if (m != null)
			delete(m);
	}
	
	/**
	 * Deletes the manufacturer.
	 * 
	 * @param m the manufacturer to delete
	 */
	public void delete(Manufacturer m)
	{
		for (Article a : m.getArticles())
		{
			a.setManufacturer(null);
			articleDAO.update(a);
		}
		this.getCurrentSession().delete(m);
		this.getCurrentSession().flush();
	}
}
