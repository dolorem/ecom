package com.smiechmateusz.dao;

import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Image;

@Transactional
public class ImageDAO extends AbstractDAO
{
	public ImageDAO()
	{
		super(Image.class);
	}
}
