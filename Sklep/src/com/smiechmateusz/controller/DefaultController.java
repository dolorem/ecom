package com.smiechmateusz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController
{
	@RequestMapping(method = RequestMethod.GET, value = "/index")
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("template");
		return mav;
	}
}
