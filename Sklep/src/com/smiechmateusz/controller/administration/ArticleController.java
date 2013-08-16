package com.smiechmateusz.controller.administration;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.dao.ImageDAO;
import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.ArticleFormModel;


@Controller
@RequestMapping("/administrator/articles/")
public class ArticleController 
{
	@Autowired
	ArticleDAO articleDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ImageDAO imageDAO;
	private final int pageSize = 10;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("admin/articles/index");
		
		return mav;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/articles/editItem");
		mav.addObject("article", new ArticleFormModel());
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editIndex(HttpServletRequest request)
	{
		System.out.println("Good, here!");
		ModelAndView mav = new ModelAndView("admin/articles/editIndex");
		System.out.println("main edit page");
		List<Article> results = articleDAO.getAll();
		System.out.println(results.size());
		PagedListHolder pagedListHolder = new PagedListHolder(results);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		System.out.println(page);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		mav.addObject("pagedListHolder", pagedListHolder);
		if (request.getSession().getAttribute("success") != null)
		{
			mav.addObject("success", request.getSession().getAttribute("success"));
			request.getSession().setAttribute("success", null);
		}
		return mav;
	}
	
	@RequestMapping(value="edit/{productId}", method=RequestMethod.GET)
	public ModelAndView editItem(@PathVariable("productId") long id)
	{
		ModelAndView mav = new ModelAndView("admin/articles/editItem");
		Article article = (Article) articleDAO.getById(id);
		if (article != null)
			mav.addObject("article", new ArticleFormModel(article));
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView acceptEdit(@ModelAttribute ArticleFormModel model, HttpServletRequest request)
	{
		Article a = (Article) articleDAO.getById(model.getId());
		if (a != null)
			model.parseModel(a, articleDAO, categoryDAO, imageDAO);
		else
			model.parseModel(new Article(), articleDAO, categoryDAO, imageDAO);
		request.getSession().setAttribute("success", "Artykuł został zapisany.");
		return new ModelAndView("redirect:/administrator/articles/edit.htm");
	}
	
	@RequestMapping(value="delete/{productId}", method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable("productId") long id)
	{
		ModelAndView mav = new ModelAndView("admin/articles/deleteConfirm");
		mav.addObject("article", (Article) articleDAO.getById(id));
		return mav;
	}
	
	@RequestMapping(value="delete/{productId}/confirm", method=RequestMethod.GET)
	public ModelAndView deleteConfirmed(@PathVariable("productId") long id, HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("redirect:/administrator/articles/edit.htm");
		articleDAO.deleteById(id);
		request.getSession().setAttribute("success", "Artykuł został usunięty.");
		return mav;
	}
	
	@RequestMapping(value="delete.json", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public Boolean deleteAjaxMultiple(@RequestBody long[] gotten)
	{
		System.out.println("HERE");
		for (long l : gotten)
			articleDAO.deleteById(l);
		return true;
	}
}
