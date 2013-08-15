package com.smiechmateusz.controller.administration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Image;



@Controller
@RequestMapping("/administrator/articles/")
public class ArticleController 
{
	@Autowired
	ArticleDAO articleDAO;
	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("admin/articles/index");
		
		return mav;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add()
	{
		ModelAndView mav = new ModelAndView("admin/articles/add");
		mav.addObject("categories", categoryDAO.getItemOffsetAlphabeticalList());
		return mav;
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/articles/add");
		ArrayList<Image> imageList = new ArrayList<Image>();
		Article article = new Article();
		article.setAvailable(false);
		try 
		{
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
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
	            	String value = item.getString();
	            	System.out.println(fieldname + " " + value);
	            	if ("name".equals(fieldname))
	            		article.setName(value);
	            	else if ("description".equals(fieldname))
	            		article.setDescription(value);
	            	else if ("available".equals(fieldname) && "on".equals(value))
	            		article.setAvailable(true);
	            }
	        }
	    } 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		article.setImages(imageList);
		article.setAddDate(new Date());
		articleDAO.create(article);
		return mav;
	}
}
