package com.smiechmateusz.controller.administration;

import java.util.List;

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

import com.smiechmateusz.dao.SiteDAO;
import com.smiechmateusz.model.Site;
import com.smiechmateusz.model.form.SiteValidator;
import com.smiechmateusz.utils.WebUtils;

/**
 * Controller handling site addition, edition and deletion.
 *  
 * @author Śmiech Mateusz
 */
@Controller
@RequestMapping(value = "/administrator/site/")
public class SiteController
{
	/** The site DAO. */
	@Autowired
	private SiteDAO siteDAO;
	
	/** The site validator */
	@Autowired
	private SiteValidator validator;
	
	/**
	 * Renders the new page form.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add()
	{
		ModelAndView mav = new ModelAndView("admin/site/edit");
		mav.addObject("site", new Site());
		return mav;
	}
	
	/**
	 * Handles site edition and forwards user to the summary page.
	 * 
	 * @param model the model containing data sent by user
	 * @param errors model binding and validation errors
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view forwarding to a summary page
	 */
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView acceptEdit(@ModelAttribute Site model, BindingResult errors, HttpServletRequest request)
	{
		validator.validate(model, errors);
		if (errors.hasErrors())
		{
			ModelAndView mav = new ModelAndView("admin/site/edit");
			mav.addObject("site", model);
			return mav;
		}
		Site s = (Site) siteDAO.getById(model.getId());
		if (s != null)
			siteDAO.update(model);
		else
			siteDAO.create(model);
		WebUtils.addSuccess("Strona została zapisana.", request);
		return new ModelAndView("redirect:/administrator/site/summary.htm");
	}
	
	/**
	 * Renders the site edition form.
	 * 
	 * @param id the id of the site to be edited
	 * @return the model and view
	 */
	@RequestMapping(value="edit/{siteId}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("siteId") long id)
	{
		Site s = (Site) siteDAO.getById(id);
		ModelAndView mav = new ModelAndView("admin/site/edit");
		mav.addObject("site", s);
		return mav;
	}
	
	/**
	 * Renders page containing summary of all pages and possible operations
	 * 
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 */
	@RequestMapping(value="summary", method=RequestMethod.GET)
	public ModelAndView summary(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/site/summary");
		List<Site> sites = siteDAO.getAll();
		mav.addObject("sites", sites);
		WebUtils.handleSuccess(request, mav);
		return mav;
	}
	
	/**
	 * Handles single item deletion via AJAX.
	 * 
	 * @param gotten the ID of site to delete
	 * @param request HttpServletRequest injected by Spring
	 * @return true
	 */
	@RequestMapping(value="delete.json", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public Boolean deleteAjaxMultiple(@RequestBody long gotten, HttpServletRequest request)
	{
		System.out.println("here");
		siteDAO.deleteById(gotten);
		WebUtils.addSuccess("Strona została usunięta.", request);
		return true;
	}
}
