package com.smiechmateusz.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


/**
 * The Class WebUtils containing methods for use in every servlet.
 * 
 * @author Śmiech Mateusz
 */
public class WebUtils
{
	
	/**
	 * Save file twice - once in server dir and once in workspace so Eclipse won't remove it during publish.
	 * 
	 * @param f
	 *            the file
	 */
	public static void saveFileTwice(CommonsMultipartFile f)
	{
		saveFile(f, System.getProperty("catalina.base") + "/wtpwebapps/Sklep/media/uploadedImages/");
		saveFile(f, "/home/mateusz/git/ecom/Sklep/WebContent/media/uploadedImages/");
	}
	
	/**
	 * Save file.
	 * 
	 * @param f
	 *            the file
	 * @param root
	 *            the path
	 */
	public static void saveFile(CommonsMultipartFile f, String root)
	{
		try
		{
			InputStream is = f.getInputStream();
			//TODO unikatowe nazwy plików		
			//TODO skalowanie obrazków
			File outf = new File(root + f.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(outf);
			while (true)
			{
				int b = is.read();
				if (b == -1)
					break;
				fos.write(b);
			}
			is.close();
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	
	/**
	 * Removes the file twice - once on the server and once in workspace so Eclipse won't recreate it during publish.
	 * 
	 * @param filename
	 *            the filename
	 */
	public static void removeFileTwice(String filename)
	{
		removeFile(System.getProperty("catalina.base") + "/wtpwebapps/Sklep/media/uploadedImages/" + filename);
		removeFile("/home/mateusz/git/ecom/Sklep/WebContent/media/uploadedImages/" + filename);			
	}
	
	/**
	 * Removes the file.
	 * 
	 * @param path
	 *            the path
	 */
	public static void removeFile(String path)
	{
		try
		{
			File f = new File(path);
			f.delete();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds success message set by addSuccess() to model.
	 * 
	 * @see WebUtils#addSuccess(String, HttpServletRequest)
	 * @param request HttpServletRequest injected by Spring
	 * @param mav ModelAndView representing a page which will be rendered
	 */
	public static void handleSuccess(HttpServletRequest request, ModelAndView mav)
	{
		if (request.getSession().getAttribute("success") != null)
		{
			mav.addObject("success", request.getSession().getAttribute("success"));
			request.getSession().setAttribute("success", null);
		}
	}
	
	/**
	 * Adds success message set by addError() to model.
	 * 
	 * @see WebUtils#addError(String, HttpServletRequest)
	 * @param request HttpServletRequest injected by Spring
	 * @param mav ModelAndView representing a page which will be rendered
	 */
	public static void handleError(HttpServletRequest request, ModelAndView mav)
	{
		if (request.getSession().getAttribute("error") != null)
		{
			mav.addObject("error", request.getSession().getAttribute("error"));
			request.getSession().setAttribute("error", null);
		}
	}
	
	/**
	 * Adds success and error messages set by addSuccess() and addError() to model  
	 * 
	 * @see WebUtils#addError(String, HttpServletRequest)
	 * @see WebUtils#addSuccess(String, HttpServletRequest)
	 * @see WebUtils#handleError(HttpServletRequest, ModelAndView)
	 * @see WebUtils#handleSuccess(HttpServletRequest, ModelAndView)
	 * @param request HttpServletRequest injected by Spring
	 * @param mav the model and view representing a page which will be rendered
	 */
	public static void handleSuccessAndError(HttpServletRequest request, ModelAndView mav)
	{
		handleSuccess(request, mav);
		handleError(request, mav);
	}
	
	/**
	 * Adds success message for further retrieval by handleSuccess.
	 * 
	 * @see WebUtils#handleSuccess(HttpServletRequest, ModelAndView)
	 * @param message the success message
	 * @param request HttpServletRequest injected by Spring
	 */
	public static void addSuccess(String message, HttpServletRequest request)
	{
		request.getSession().setAttribute("success", message);
	}
	
	/**
	 * Adds error message for further retrieval by handleError.
	 * 
	 * @see WebUtils#handleError(HttpServletRequest, ModelAndView)
	 * @param message the error message
	 * @param request HttpServletRequest injected by Spring
	 */
	public static void addError(String message, HttpServletRequest request)
	{
		request.getSession().setAttribute("error", message);
	}
}
