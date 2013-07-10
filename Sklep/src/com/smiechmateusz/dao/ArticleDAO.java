package com.smiechmateusz.dao;

import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Article;

@Transactional
public class ArticleDAO extends AbstractDAO
{
	public ArticleDAO()
	{
		super(Article.class);
	}
}
