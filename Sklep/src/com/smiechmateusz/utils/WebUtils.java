package com.smiechmateusz.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class WebUtils
{
	public static void saveFileTwice(CommonsMultipartFile f)
	{
		saveFile(f, System.getProperty("catalina.base") + "/wtpwebapps/Sklep/media/uploadedImages/");
		saveFile(f, "/home/mateusz/git/ecom/Sklep/WebContent/media/uploadedImages/");
	}
	
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
	
	public static void removeFileTwice(String filename)
	{
		removeFile(System.getProperty("catalina.base") + "/wtpwebapps/Sklep/media/uploadedImages/" + filename);
		removeFile("/home/mateusz/git/ecom/Sklep/WebContent/media/uploadedImages/" + filename);			
	}
	
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
