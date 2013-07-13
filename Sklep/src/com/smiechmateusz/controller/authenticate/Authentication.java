package com.smiechmateusz.controller.authenticate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.smiechmateusz.dao.UserDAO;

@Aspect
public class Authentication implements ApplicationContextAware
{

	ApplicationContext context;
	
	@Before(value="@annotation(ra)")
	public void foobar(JoinPoint jp, RequireAuthentication ra) throws Throwable
	{
		System.out.println(ra.authenticationLevel());
		System.out.println("here");
	}

	public void logIn(String username, String password)
	{
		System.out.println("Here");
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException
	{
		this.context = context;	
	}
}