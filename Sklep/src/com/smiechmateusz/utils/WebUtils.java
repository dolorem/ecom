package com.smiechmateusz.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


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
}
