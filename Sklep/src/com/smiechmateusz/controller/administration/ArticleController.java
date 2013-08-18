package com.smiechmateusz.controller.administration;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.dao.ImageDAO;
import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.ArticleFormModel;


/**
 * Controller handling article addition, edition and deletion.
 * 
 * @author Śmiech Mateusz
 */
@Controller
@RequestMapping("/administrator/articles/")
public class ArticleController 
{
	
	/** The article dao. */
	@Autowired
	ArticleDAO articleDAO;
	
	/** The category dao. */
	@Autowired
	CategoryDAO categoryDAO;
	
	/** The image dao. */
	@Autowired
	ImageDAO imageDAO;
	
	/** The page size. */
	private final int pageSize = 10;
	
	/**
	 * Renders index page containing list of possible activities.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("admin/articles/index");
		return mav;
	}
	
	/**
	 * Renders the new item form.
	 * 
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/articles/editItem");
		mav.addObject("article", new ArticleFormModel());
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	/**
	 * Renders page containing summary of all articles and possible operations.
	 * 
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editIndex(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/articles/editIndex");
		List<Article> results = articleDAO.getAll();
		processPagination(request, mav, results);
		handleSuccess(request, mav);
		return mav;
	}

	/**
	 * Handles pagination utilizing Spring PagedListHolder.
	 * 
	 * @see org.springframework.beans.support.PagedListHolder;
	 * @param request HttpServletRequest containing page parameter
	 * @param mav ModelAndView representing a page which will be rendered
	 * @param results
	 */
	private void processPagination(HttpServletRequest request,
			ModelAndView mav, List results)
	{
		PagedListHolder pagedListHolder = new PagedListHolder(results);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		mav.addObject("pagedListHolder", pagedListHolder);
	}

	/**
	 * Moves success message from session to model.
	 * 
	 * @param request HttpServletRequest injected by Spring
	 * @param mav ModelAndView representing a page which will be rendered
	 */
	private void handleSuccess(HttpServletRequest request, ModelAndView mav)
	{
		if (request.getSession().getAttribute("success") != null)
		{
			mav.addObject("success", request.getSession().getAttribute("success"));
			request.getSession().setAttribute("success", null);
		}
	}
	
	/**
	 * Renders the item edition form.
	 * 
	 * @param id the id of the item to be edited
	 * @return the model and view
	 */
	@RequestMapping(value="edit/{productId}", method=RequestMethod.GET)
	public ModelAndView editItem(@PathVariable("productId") long id)
	{
		ModelAndView mav = new ModelAndView("admin/articles/editItem");
		Article article = (Article) articleDAO.getById(id);
		if (article != null) //article doesn't exist, we want to create a new one
			mav.addObject("article", new ArticleFormModel(article)); //initialize the form with an empty ArticleFormModel
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	/**
	 * Handles article edition and forwards user to a summary page.
	 * 
	 * @param model the model containing data sent by the user
	 * @param request HttpServletRequest injected by Spring
	 * @return the model and view forwarding to a summary page
	 */
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView acceptEdit(@ModelAttribute ArticleFormModel model, HttpServletRequest request)
	{
		Article a = (Article) articleDAO.getById(model.getId());
		if (a != null) //article already exists
			model.parseModel(a, articleDAO, categoryDAO, imageDAO);
		else //article doesn't exist
		{
			Article art = new Article(); //we have to create it
			articleDAO.create(art);  //and persist via Hibernate
			model.parseModel(art, articleDAO, categoryDAO, imageDAO); //so that there won't be ID and/or dependency issues
		}
		request.getSession().setAttribute("success", "Artykuł został zapisany.");
		return new ModelAndView("redirect:/administrator/articles/edit.htm");
	}
	
	/**
	 * Handles multiple item deletion via AJAX.
	 * 
	 * @param gotten long array containing IDs of elements to be deleted, decoded from JSON
	 * @return true
	 */
	@RequestMapping(value="delete.json", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public Boolean deleteAjaxMultiple(@RequestBody long[] gotten)
	{
		for (long l : gotten)
			articleDAO.deleteById(l);
		return true;
	}
}
