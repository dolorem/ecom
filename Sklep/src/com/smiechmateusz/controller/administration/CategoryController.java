package com.smiechmateusz.controller.administration;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.model.Category;
import com.smiechmateusz.utils.Pair;


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
		ArrayList<Category> categories = categoryDAO.loadRootAlphabetically();
		ArrayList<Pair<Category, Integer>> cat = new ArrayList<Pair<Category, Integer>>();
		Stack<Pair<Category, Integer>> q = new Stack<Pair<Category, Integer>>();
		for (int i = categories.size() - 1; i >= 0; i--)
			q.add(new Pair<Category, Integer>(categories.get(i), 0));
		while (!q.empty())
		{
			Pair<Category, Integer> pair = q.pop();
			List<Category> children = pair.getLeft().getChildrenAlphabetically();
			if (children != null)
			{
				for (int i = children.size() - 1; i >= 0; i--)
					q.add(new Pair<Category, Integer>(children.get(i), pair.getRight() + 1));
				cat.add(pair);
			}
		}
		System.out.println("categories (" + categories.size() + ")");
		for (int i = 0; i < categories.size(); i++)
			System.out.println(categories.get(i).getName());
		mav.addObject("categories", cat);
		System.out.println(categories.size() + " == " + cat.size());
		return mav;
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/categories/add");
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
