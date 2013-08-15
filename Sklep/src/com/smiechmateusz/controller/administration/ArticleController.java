package com.smiechmateusz.controller.administration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Category;
import com.smiechmateusz.model.Image;
import com.smiechmateusz.model.form.FormArticleModel;



@Controller
@RequestMapping("/administrator/articles/")
public class ArticleController 
{
	@Autowired
	ArticleDAO articleDAO;
	@Autowired
	CategoryDAO categoryDAO;
	private final int pageSize = 10;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("admin/articles/index");
		
		return mav;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/articles/add");
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		System.out.println("a " + request.getAttribute("css") + " " + request.getParameter("css"));
		return mav;
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}
		catch (UnsupportedEncodingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("desc" + request.getAttribute("description"));
		ArrayList<Image> imageList = new ArrayList<Image>();
		Article article = new Article();
		article.setAvailable(false);
		ArrayList<Category> categories = new ArrayList<Category>();
		try 
		{
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
			sfu.setHeaderEncoding("UTF-8"); 
	        List<FileItem> items = sfu.parseRequest(request);
	        for (FileItem item : items) 
	        {
	            if (!item.isFormField()) 
	            {
	            	String fieldname = item.getFieldName();
	                String filename = FilenameUtils.getName(item.getName());
	                imageList.add(new Image(article, filename, "mainImage".equals(fieldname) ? Image.TYPE_MAIN : Image.TYPE_ADDITIONAL));
	                InputStream filecontent = item.getInputStream();
	                File f = new File(System.getProperty("catalina.base") + "/wtpwebapps/Sklep/media/uploadedImages/"+ filename);
	                FileOutputStream fos = new FileOutputStream(f);
	                while (true)
	                {
	                	int b = filecontent.read();
	                	if (b != -1)
	                		fos.write(b);
	                	else
	                		break;
	                }
	                fos.close();
	                filecontent.close();
	                System.out.println(fieldname + " " + filename);
	            }
	            else
	            {
	            	String fieldname = item.getFieldName();
	            	String value = item.getString("UTF-8");
	            	System.out.println(fieldname + " " + value);
	            	if ("name".equals(fieldname))
	            		article.setName(value);
	            	else if ("description".equals(fieldname))
	            		article.setDescription(value);
	            	else if ("available".equals(fieldname) && "on".equals(value))
	            		article.setAvailable(true);
	            	else if ("categories".equals(fieldname))
	            	{
	            		try
	            		{
	            			long id = Long.parseLong(value);
	            			Category c = (Category) categoryDAO.getById(id);
	            			if (c != null)
	            				categories.add(c);
	            		}
	            		catch (Exception e)
	            		{
	            			
	            		}
	            	}
	            }
	        }
	    } 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		article.setCategories(categories);
		article.setImages(imageList);
		article.setAddDate(new Date());
		articleDAO.create(article);
		return new ModelAndView("redirect:/administrator/articles/add.htm");
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editIndex(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/articles/editIndex");
		System.out.println("main edit page");
		List<Article> results = articleDAO.getAll();
		System.out.println(results.size());
		PagedListHolder pagedListHolder = new PagedListHolder(results);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		System.out.println(page);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		mav.addObject("pagedListHolder", pagedListHolder);
		return mav;
	}
	
	@RequestMapping(value="edit/{productId}", method=RequestMethod.GET)
	public ModelAndView editItem(@PathVariable("productId") long id)
	{
		ModelAndView mav = new ModelAndView("admin/articles/editItem");
		Article article = (Article) articleDAO.getById(id);
		if (article != null)
			mav.addObject("article", article);
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		for (int i = 0; i < article.getCategories().size(); i++)
			System.out.println(article.getCategories().get(i).getId());
		return mav;
	}
	
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView acceptEdit(@ModelAttribute FormArticleModel model)
	{
		System.out.println(model.getName() + " " + model.getDescription() + " " + model.isAvailable() + " " + model.getId());
		Article a = (Article) articleDAO.getById(model.getId());
		if (a != null)
		{
			a.setDescription(model.getDescription());
			a.setName(model.getName());
			a.setAvailable(model.isAvailable());
			ArrayList<Category> categories = new ArrayList<Category>();
			for (Long i : model.getCategories())
			{
				Category c = (Category) categoryDAO.getById(i);
				if (c != null)
					categories.add(c);
			}
			a.setCategories(categories);
			articleDAO.update(a);
		}
		return new ModelAndView("redirect:/administrator/articles/edit.htm");
	}
}
