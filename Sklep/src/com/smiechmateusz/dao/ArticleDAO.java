package com.smiechmateusz.dao;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Category;

@Transactional
public class ArticleDAO extends AbstractDAO
{
	public ArticleDAO()
	{
		super(Article.class);
	}
}
