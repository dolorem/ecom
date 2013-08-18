package com.smiechmateusz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.model.Article;

/**
 * Controller handling site article requests.
 * 
 * @author Śmiech Mateusz
 */
@Controller
@RequestMapping("/article/")
public class ArticleSiteController
{
	
	/** The article dao. */
	@Autowired
	ArticleDAO articleDAO;
	
	/**
	 * View article.
	 * 
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 * @throws ResourceNotFoundException if article of given ID doesn't exist
	 */
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView viewArticle(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("viewArticle");
		Article a = new Article();
		a = (Article) articleDAO.getById(Long.parseLong(request.getParameter("id")));
		if (a == null)
			throw new ResourceNotFoundException(); //404 Error
		mav.addObject("article", a);
		return mav;
	}
	
}
