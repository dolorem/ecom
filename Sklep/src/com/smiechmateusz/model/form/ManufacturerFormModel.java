package com.smiechmateusz.model.form;

import com.smiechmateusz.dao.ManufacturerDAO;
import com.smiechmateusz.model.Manufacturer;

/**
 * Manufacturer model for use in forms. Contains both original and target data.
 * 
 * @author Śmiech Mateusz
 */
public class ManufacturerFormModel extends Manufacturer
{
	/**
	 * Instantiates a new manufacturer form model.
	 */
	public ManufacturerFormModel()
	{
		super();
	}
	
	/**
	 * Instantiates a new manufacturer form model from a given manufacturer.
	 * 
	 * @param m the manufacturer
	 */
	public ManufacturerFormModel(Manufacturer m)
	{
		this.id = m.getId();
		this.articles = m.getArticles();
		this.description = m.getDescription();
		this.name = m.getName();
	}
	
	/**
	 * Parses the model and updates the manufacturer.
	 * 
	 * @param m the target manufacturer
	 * @param manufacturerDAO the manufacturer dao
	 */
	public void parseModel(Manufacturer m, ManufacturerDAO manufacturerDAO)
	{
		m.setDescription(this.getDescription());
		m.setName(this.getName());
		manufacturerDAO.update(m);
	}
}
