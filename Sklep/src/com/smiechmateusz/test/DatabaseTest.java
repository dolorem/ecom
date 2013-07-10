package com.smiechmateusz.test;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.dao.ImageDAO;
import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Image;

public class DatabaseTest
{
	@Test
	public void DBTest()
	{
		
//		try
//		{
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-hibernate.xml");
		Configuration config = (Configuration) context.getBean("hibernateConfiguration");
		new SchemaExport(config).create(true, true);
		ArticleDAO ad = (ArticleDAO) context.getBean("ArticleDAO");
		ImageDAO id = (ImageDAO) context.getBean("ImageDAO");
		Article a = new Article();
		Image i = new Image();
		i.setPath("path");
		i.setType(0);
		i.setArticle(a);
		id.create(i);
		a.setAddDate(new Date());
		a.setDescription("desc");
		Image i2 = new Image();
		i2.setPath("path2");
		i2.setType(1);
		id.create(i2);
		ArrayList<Image> list = new ArrayList<Image>();
		list.add(i);
		list.add(i2);
		a.setImages(list);
		ad.create(a);
		ArrayList<Article> ar = (ArrayList<Article>) ad.getAll();
		System.out.println(ar.size());
		Article arr = ar.get(0);
		System.out.println(arr.getImages().get(0).getId() + " " + arr.getImages().get(1).getId());
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}
}
