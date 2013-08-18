package com.smiechmateusz.controller.administration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Default controller handling admin index page.
 * 
 * @author Śmiech Mateusz
 */
@Controller
@RequestMapping(value = "/administrator/")
public class IndexController
{
	
	/**
	 * Renders admin index page.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("/admin/index");
		
		return mav;
	}
}
