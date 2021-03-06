package com.smiechmateusz.controller.administration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.model.Category;
import com.smiechmateusz.model.form.CategoryFormModel;
import com.smiechmateusz.model.form.CategoryValidator;
import com.smiechmateusz.utils.WebUtils;

/**
 * Controller handling category addition, edition and deletion.
 * 
 * @author Śmiech Mateusz
 */
@Controller
@RequestMapping("/administrator/categories/")
public class CategoryController
{
	/** The validator. */
	@Autowired
	private CategoryValidator validator;
	
	/** The category dao. */
	@Autowired
	private CategoryDAO categoryDAO;

	/**
	 * Renders index page listing possible activities.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("admin/categories/index");
		return mav;
	}
	
	/**
	 * Renders the form for a new category addition.
	 * 
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/categories/edit");
		WebUtils.handleSuccessAndError(request, mav);
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		mav.addObject("categoryFormModel", new CategoryFormModel());
		return mav;
	}
	
	/**
	 * Renders the summary page.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editIndex()
	{
		ModelAndView mav = new ModelAndView("admin/categories/editIndex");
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	/**
	 * Handles article edit.
	 * 
	 * @param model the model containing target data
	 * @param errors model binding and validation results
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view redirecting the user to summary page or showing an error
	 */
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView acceptEdit(@ModelAttribute CategoryFormModel model, BindingResult errors, HttpServletRequest request)
	{
		validator.validate(model, errors);
		if (errors.hasErrors())
		{
			ModelAndView mav = new ModelAndView("admin/categories/edit");
			mav.addObject("categoryFormModel", model);
			mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
			return mav;
		}
		Category c = (Category) categoryDAO.getById(model.getId());
		if (c != null) //category is being edited
			model.parseModel(c, categoryDAO);
		else //it's a new category
		{
			Category cat = new Category();
			categoryDAO.create(cat);
			model.parseModel(cat, categoryDAO);
		}
		WebUtils.addSuccess("Kategoria została zapisana.", request);
		return new ModelAndView("redirect:/administrator/categories/edit.htm");
	}
	
	/**
	 * Renders single item edition form.
	 * 
	 * @param id the id of the category to edit
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 */
	@RequestMapping(value="edit/{productId}", method=RequestMethod.GET)
	public ModelAndView editItem(@PathVariable("productId") long id, HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/categories/edit");
		Category category= (Category) categoryDAO.getById(id);
		if (category != null)
			mav.addObject("categoryFormModel", new CategoryFormModel(category));
		WebUtils.handleError(request, mav);
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	/**
	 * Deletes multiple categories of given IDs parsed from JSON request.
	 * 
	 * @param gotten the array of IDs of categories to delete
	 * @return true
	 */
	@RequestMapping(value="delete.json", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public Boolean deleteAjaxMultiple(@RequestBody long[] gotten, HttpServletRequest request)
	{
		for (long l : gotten)
			categoryDAO.deleteById(l);
		WebUtils.addSuccess("Kategoria została usunięta.", request);
		return true;
	}
}
