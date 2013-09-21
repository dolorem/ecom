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

import com.smiechmateusz.dao.MenuDAO;
import com.smiechmateusz.model.Category;
import com.smiechmateusz.model.Menu;
import com.smiechmateusz.model.form.CategoryFormModel;
import com.smiechmateusz.model.form.MenuFormModel;
import com.smiechmateusz.model.form.MenuValidator;
import com.smiechmateusz.utils.WebUtils;

/**
 * Controller handling Menu addition, edition and deletion.
 * 
 * @author Śmiech Mateusz
 */
@Controller
@RequestMapping("administrator/menu")
public class MenuController
{
	
	/** The menu dao. */
	@Autowired
	MenuDAO menuDAO;
	
	/** The validator. */
	@Autowired
	private MenuValidator validator;
	
	/**
	 * Renders the form for a new menu item addition.
	 * 
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "add")
	public ModelAndView add(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/menu/add");
		WebUtils.handleSuccess(request, mav);
		mav.addObject("menuFormModel", new MenuFormModel());
		mav.addObject("menuOffset", menuDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	/**
	 * Renders the index page.
	 * 
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "edit")
	public ModelAndView index(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/menu/index");
		WebUtils.handleSuccess(request, mav);
		mav.addObject("menuItems", menuDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	/**
	 * Handles menu item edit.
	 * 
	 * @param model the model containing target data
	 * @param errors model binding and validation results
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view redirecting the user to the summary page or showing an error
	 */
	@RequestMapping(method = RequestMethod.POST, value = "edit")
	public ModelAndView acceptEdit(@ModelAttribute MenuFormModel model, BindingResult errors, HttpServletRequest request)
	{
		validator.validate(model, errors);
		if (errors.hasErrors())
		{
			ModelAndView mav = new ModelAndView("admin/menu/add");
			mav.addObject("menuFormModel", model);
			mav.addObject("menuOffset", menuDAO.getItemOffsetAlphabeticalList());
			return mav;
		}
		Menu menu = (Menu) menuDAO.getById(model.getId());
		if (menu != null) //menu already exists
			model.parseModel(menu, menuDAO);
		else //menu doesn't exist
		{
			menu = new Menu(); //we have to create it
			menuDAO.create(menu);  //and persist via Hibernate
			model.parseModel(menu, menuDAO); //so that there won't be ID and/or dependency issues
		}
		WebUtils.addSuccess("Przedmiot menu został zapisany.", request);
		return new ModelAndView("redirect:/administrator/menu/edit.htm");
	}
	
	/**
	 * Renders the single item edition form.
	 * 
	 * @param id the id of the item to edit
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 */
	@RequestMapping(value="edit/{productId}", method=RequestMethod.GET)
	public ModelAndView editItem(@PathVariable("productId") long id, HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/menu/add");
		WebUtils.handleSuccess(request, mav);
		Menu category= (Menu) menuDAO.getById(id);
		if (category != null)
			mav.addObject("menuFormModel", new MenuFormModel(category));
		mav.addObject("menuOffset", menuDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	/**
	 * Deletes single menu item of given id by an AJAX call.
	 * 
	 * @param gotten the id of the item to delete
	 * @param request HttpServletRequest injected by Spring
	 * @return true
	 */
	@RequestMapping(value="delete.json", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public Boolean delete(@RequestBody long gotten, HttpServletRequest request)
	{
		menuDAO.deleteById(gotten);
		WebUtils.addSuccess("Element menu został usunięty.", request);
		return true;
	}
}
