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

import com.smiechmateusz.dao.ManufacturerDAO;
import com.smiechmateusz.model.Manufacturer;
import com.smiechmateusz.model.ManufacturerFormModel;

/**
 * Handles manufacturer addition, edition and deletion.
 * 
 * @author Śmiech Mateusz
 */
@Controller
@RequestMapping(value="/administrator/manufacturers/")
public class ManufacturerController
{
	/** The manufacturer dao. */
	@Autowired
	ManufacturerDAO manufacturerDAO;
	
	/**
	 * Renders summary page.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value="view", method=RequestMethod.GET)
	public ModelAndView summary()
	{
		//TODO success and errors
		ModelAndView mav = new ModelAndView("admin/manufacturer/summary");
		mav.addObject("manufacturers", manufacturerDAO.getAllAlphabeticall());
		return mav;		
	}
	
	/**
	 * Renders manufacturer addition form.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add()
	{
		ModelAndView mav = new ModelAndView("admin/manufacturer/edit");
		mav.addObject("manufacturer", new ManufacturerFormModel());
		return mav;
	}
	
	 /**
	  * Renders manufacturer edition form.
	  * 
	  * @param id the id of manufacturer to edit
	  * @return the model and view
	  */
	 @RequestMapping(value="edit/{id}", method=RequestMethod.GET)
	 public ModelAndView edit(@PathVariable("id") long id)
	 {
		 ModelAndView mav = new ModelAndView("admin/manufacturer/edit");
		 Manufacturer m = manufacturerDAO.getById(id);
		 if (m != null)
			 mav.addObject("manufacturer", new ManufacturerFormModel(m));
		 return mav;
	 }
	 
	 /**
	  * Edits the manufacturer and redirects the user to a summary page.
	  * 
	  * @param model the model containing data sent by user
	  * @param request HttpServletRequest injected by Spring
	  * @return the model and view forwarding to a summary page
	  */
	 @RequestMapping(value="edit", method=RequestMethod.POST)
	 public ModelAndView acceptEdit(@ModelAttribute ManufacturerFormModel model, HttpServletRequest request)
	 {
		 Manufacturer m = manufacturerDAO.getById(model.getId());
		 if (m == null)
		 {
			 m = new Manufacturer();
			 manufacturerDAO.create(m);
		 }
		 model.parseModel(m, manufacturerDAO);
		 request.getSession().setAttribute("success", "Producent został dodany.");
		 return new ModelAndView("redirect:/administrator/manufacturers/view.htm");
	 }
	 
	 /**
	  * Deletes manufacturers of given IDs parsed from JSON object.
	  * 
	  * @param gotten the array of IDs
	  * @return true
	  */
	 @RequestMapping(value="delete.json", method=RequestMethod.DELETE, produces="application/json")
	 @ResponseBody
	 public Boolean deleteAjaxMultiple(@RequestBody long[] gotten)
	 {
		 for (long l : gotten)
			 manufacturerDAO.deleteById(l);
		 return true;
	 }
}
