package com.smiechmateusz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Image;
import com.smiechmateusz.utils.WebUtils;

/**
 * AbstractDAO implementation for Article overriding some of its methods.
 * 
 *  @author Śmiech Mateusz
 */
@Transactional
public class ArticleDAO extends AbstractDAO
{
	
	/** The image dao. */
	@Autowired
	ImageDAO imageDAO;
	
	/**
	 * Instantiates a new article dao.
	 */
	public ArticleDAO()
	{
		super(Article.class);
	}
	
	/**
	 * Deletes the Article by its ID.
	 * 
	 * @param id the id of the Article item
	 */
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
