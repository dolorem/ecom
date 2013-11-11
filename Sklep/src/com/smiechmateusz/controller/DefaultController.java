package com.smiechmateusz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.SiteDAO;
import com.smiechmateusz.model.Site;

/**
 * Default controller handling main page.
 * 
 * @author Śmiech Mateusz
 */
@Controller
public class DefaultController
{
	@Autowired
	SiteDAO siteDAO;
	
	
	/**
	 * Renders index page.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/index")
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("template");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/**")
	public ModelAndView page(HttpServletRequest request)
	{
		String url = getRelativeUrl(request);
//		url = url.substring(0, url.length() - 4);
		Site s = siteDAO.getWithPath(url);
		if (s == null)
			throw new ResourceNotFoundException();
		ModelAndView mav = new ModelAndView("customSite");
		mav.addObject("site", s);
		return mav;
	}
	
	public static String getRelativeUrl(
		    HttpServletRequest request ) {

		    String baseUrl = null;

		    baseUrl =
		        request.getScheme() + "://" +
		        request.getServerName() + ":" + request.getServerPort() +
		        request.getContextPath();

		    StringBuffer buf = request.getRequestURL();

		    if ( request.getQueryString() != null ) {
		      buf.append( "?" );
		      buf.append( request.getQueryString() );
		    }

		    return buf.substring( baseUrl.length() );
		  }

}
