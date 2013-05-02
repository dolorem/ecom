package com.smiechmateusz.dao;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class temp 
{
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Configuration c = (Configuration) context.getBean("hibernateConfiguration");
		//new SchemaExport(c).create(true, true);
		FooDAO fd = (FooDAO) context.getBean("FooDAO");
		
		for (int i = 0; i < 10; i++)
		{
			Foo f  = new Foo();
			f.setX(i);
			f.setY(10 - i);
			f.setDescription("bean" + i);
			fd.create(f);
		}
	}
}
