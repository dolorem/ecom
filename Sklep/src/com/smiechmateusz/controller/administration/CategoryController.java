package com.smiechmateusz.controller.administration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.ArticleFormModel;
import com.smiechmateusz.model.Category;
import com.smiechmateusz.model.CategoryFormModel;


@Controller
@RequestMapping("/administrator/categories/")
public class CategoryController
{
	@Autowired
	CategoryDAO categoryDAO;

	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("admin/categories/index");
		
		return mav;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/categories/edit");
		if (request.getSession().getAttribute("error") != null)
		{
			mav.addObject("error", request.getSession().getAttribute("error"));
			request.getSession().setAttribute("error",  null);
		}
		if (request.getSession().getAttribute("success") != null)
		{
			mav.addObject("success", request.getSession().getAttribute("success"));
			request.getSession().setAttribute("success",  null);
		}
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		mav.addObject("category", new CategoryFormModel());
		return mav;
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editIndex()
	{
		ModelAndView mav = new ModelAndView("admin/categories/editIndex");
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView acceptEdit(HttpServletRequest request, @ModelAttribute CategoryFormModel model)
	{
		Category c = (Category) categoryDAO.getById(model.getId());
		if (c != null)
		{
			if (!model.gonnaBeSelfChild(c))
				model.parseModel(c, categoryDAO);
			else
			{
				request.getSession().setAttribute("error", "Nie można przenieść kategorii do jej podkategorii.");
				return new ModelAndView("redirect:/administrator/categories/edit/" + model.getId() + ".htm");
			}
		}
		else
		{
			Category cat = new Category();
			categoryDAO.create(cat);
			model.parseModel(cat, categoryDAO);
		}
		return new ModelAndView("redirect:/administrator/categories/edit.htm");
	}
	
	@RequestMapping(value="edit/{productId}", method=RequestMethod.GET)
	public ModelAndView editItem(@PathVariable("productId") long id, HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/categories/edit");
		Category category= (Category) categoryDAO.getById(id);
		if (category != null)
			mav.addObject("category", new CategoryFormModel(category));
		if (request.getSession().getAttribute("error") != null)
		{
			mav.addObject("error", request.getSession().getAttribute("error"));
			request.getSession().setAttribute("error", null);
		}
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	@RequestMapping(value="delete.json", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public Boolean deleteAjaxMultiple(@RequestBody long[] gotten)
	{
		for (long l : gotten)
			categoryDAO.deleteById(l);
		return true;
	}
}
