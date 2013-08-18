package com.smiechmateusz.dao;

import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Image;


/**
 * AbstractDAO implementation for Image, overriding some of its methods.
 * 
 * @author Śmiech Mateusz
 */
@Transactional
public class ImageDAO extends AbstractDAO
{
	
	/**
	 * Instantiates a new image dao.
	 */
	public ImageDAO()
	{
		super(Image.class);
	}
}
