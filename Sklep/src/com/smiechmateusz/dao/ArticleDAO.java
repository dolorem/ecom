package com.smiechmateusz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Image;
import com.smiechmateusz.utils.WebUtils;

@Transactional
public class ArticleDAO extends AbstractDAO
{
	@Autowired
	ImageDAO imageDAO;
	
	public ArticleDAO()
	{
		super(Article.class);
	}
	
	public void deleteById(long id)
	{
		final Article entity = (Article) this.getById(id);
		for (Image i : entity.getImages())
		{
			WebUtils.removeFileTwice(i.getPath());
			imageDAO.delete(i);
		}
        if (entity != null)
        	this.delete(entity);
	}
}
