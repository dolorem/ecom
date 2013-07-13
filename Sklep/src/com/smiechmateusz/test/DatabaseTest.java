package com.smiechmateusz.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smiechmateusz.dao.AbstractDAO;
import com.smiechmateusz.dao.ArticleDAO;
import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.dao.ImageDAO;
import com.smiechmateusz.dao.RoleDAO;
import com.smiechmateusz.dao.UserDAO;
import com.smiechmateusz.model.Article;
import com.smiechmateusz.model.Category;
import com.smiechmateusz.model.Image;
import com.smiechmateusz.model.MyUser;
import com.smiechmateusz.model.Role;

public class DatabaseTest
{
	@Test
	public void DBTest()
	{
		try{
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-hibernate.xml");
		Configuration config = (Configuration) context.getBean("hibernateConfiguration");
		new SchemaExport(config).create(true, true);
		ArticleDAO articleDAO = (ArticleDAO) context.getBean("ArticleDAO");
		ImageDAO imageDAO = (ImageDAO) context.getBean("ImageDAO");
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		RoleDAO roleDAO = (RoleDAO) context.getBean("RoleDAO");
		CategoryDAO categoryDAO = (CategoryDAO) context.getBean("CategoryDAO");
		Article a = new Article();
		a.setDescription("articleDescription");
		Image i = new Image();
		i.setPath("myPath");
		i.setType(7);
		a.setImages(new ArrayList<Image>());
		a.getImages().add(i);
		i.setArticle(a);
		articleDAO.create(a);
		imageDAO.create(i);
		Article gottenArticle = (Article) articleDAO.getById(1L);
		assertEquals(gottenArticle.getDescription(), a.getDescription());
		assertEquals(gottenArticle.getImages().get(0).getPath(), i.getPath());
		assertEquals(gottenArticle.getImages().get(0).getType(), i.getType());
//		articleDAO.delete(a);
//		imageDAO.delete(i);
		
		a = new Article();
		a.setDescription("My description");
		a.setCategories(new ArrayList<Category>());
		articleDAO.create(a);
		Category c = new Category();
		a.getCategories().add(c);
//		c.setParent(0);
		c.setParent(null);
		c.setArticles(new ArrayList<Article>());
		c.setName("root");
		Category c2 = new Category();
		c2.setName("subcategory");
		a.getCategories().add(c2);
		System.out.println(a.getId());
		System.out.println(a.getCategories().size());
		c.getArticles().add(a);
		c2.setArticles(c.getArticles());
		categoryDAO.create(c);
//		c2.setParent(c.getId());
		c2.setParent(c);
		categoryDAO.create(c2);
		articleDAO.update(a);
		Category gottenCategory = (Category) categoryDAO.getById(c.getId());
		assertEquals(gottenCategory, c);
		assertEquals(gottenCategory.getArticles().get(0), a);
		gottenCategory = (Category) categoryDAO.getById(c2.getId());
		assertEquals(gottenCategory, c2);
		assertEquals(gottenCategory.getArticles().get(0), a);
//		articleDAO.delete(a);
		c.setArticles(new ArrayList<Article>());
//		categoryDAO.delete(c);
//		categoryDAO.deleteById(1L);
//		categoryDAO.delete(c2);

		MyUser u = new MyUser();
		u.setEmail("email@mydomain.com");
		u.setPasswordHash("rgaeodf+);}d);]+ y=");
		u.setUsername("username");
		Role r = new Role();
		r.setDescription("my description");
		userDAO.create(u);
		roleDAO.create(r);
		r.setUsers(new ArrayList<MyUser>());
		r.getUsers().add(u);
		u.setRole(new ArrayList<Role>());
		u.getRole().add(r);
		userDAO.update(u);
		roleDAO.update(r);
		MyUser gottenUser = (MyUser) userDAO.getById(1L);
		assertEquals(gottenUser, u);
		Role gottenRole = (Role) roleDAO.getById(1L);
		assertEquals(gottenRole, r);
		//AbstractDAO.fooSession.flush();
//		userDAO.delete(u);
//		roleDAO.delete(r);
//		userDAO.deleteById(u.getId());
		System.out.println(u.getId());
//		AbstractDAO.fooSession.flush();
		System.out.println("HERE?");
		
		}
		catch(Exception e)
		{e.printStackTrace();
		System.out.println("DAMMIT");}
	}
}
