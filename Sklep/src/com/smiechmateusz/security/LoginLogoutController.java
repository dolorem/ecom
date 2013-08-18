package com.smiechmateusz.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles login and access pages with help of Spring Security.
 * 
 * @author Śmiech Mateusz
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController 
{

	/**
	 * Renders the login page.
	 * 
	 * @param error the error
	 * @param model the model
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) 
	{

		if (error == true) 
			model.put("error", "You have entered an invalid username or password!");
		else 
			model.put("error", "");
		return "loginpage";
	}

	/**
	 * Renders the denied page.
	 *
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() 
	{
		return "deniedpage";
	}
}