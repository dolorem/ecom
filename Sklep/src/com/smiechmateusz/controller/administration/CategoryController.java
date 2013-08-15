package com.smiechmateusz.controller.administration;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.model.Category;


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
	public ModelAndView add()
	{
		ModelAndView mav = new ModelAndView("admin/categories/add");
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("name = " + request.getParameter("name"));
		System.out.println(request.getParameter("primaryCategory") + " " + request.getParameter("name") + " " + request.getParameter("description"));
		Category c = new Category();
		c.setName((String) request.getParameter("name"));
		if ("0".equals((String) request.getParameter("primaryCategory")) || request.getParameter("primaryCategory") == null)
			c.setParent(null);
		else
			c.setParent((Category) categoryDAO.getById(Long.parseLong((String) request.getParameter("primaryCategory"))));
		c.setChildren(new ArrayList<Category>());
		categoryDAO.create(c);
		Category parent = c.getParent();
		if (parent != null)
		{
			parent.getChildren().add(c);
			categoryDAO.update(parent);
		}
		return new ModelAndView("redirect:/administrator/categories/add.htm");
	}
}
