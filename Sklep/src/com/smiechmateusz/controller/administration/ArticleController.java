package com.smiechmateusz.controller.administration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/administrator/articles/")
public class ArticleController 
{
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
		
		return mav;
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/articles/add");
		System.out.println("upload");
		System.out.println(request.getParameter("name"));
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (!item.isFormField()) {
	                
	                // Process form file field (input type="file").
	                String fieldname = item.getFieldName();
	                String filename = FilenameUtils.getName(item.getName());
	                InputStream filecontent = item.getInputStream();
//	                File f = new File("a.jpg");
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
	                // ... (do your job here)
	            }
	        }
	    } 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mav;
	}
}
