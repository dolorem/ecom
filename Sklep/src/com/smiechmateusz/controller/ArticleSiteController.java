package com.smiechmateusz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.model.Article;

@Controller
@RequestMapping("/article/")
public class ArticleSiteController
{
	@Autowired
	ArticleDAO articleDAO;
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView viewArticle(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("viewArticle");
		Article a = new Article();
		try 
		{
			a = (Article) articleDAO.getById(Long.parseLong(request.getParameter("id")));
		}
		catch (Exception e)
		{
			throw new ResourceNotFoundException();
		}
		if (a == null)
			throw new ResourceNotFoundException();
		mav.addObject("article", a);
		return mav;
	}
	
}
