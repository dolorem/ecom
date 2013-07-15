package com.smiechmateusz.aspect;

import java.util.ArrayList;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.MenuDAO;
import com.smiechmateusz.dao.UserDAO;
import com.smiechmateusz.model.Menu;

@Aspect
public class BaseControllerAspect implements ApplicationContextAware
{
	ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext context)
	{
		this.context = context;
	}
	
	@Around("within(com.smiechmateusz.controller.*) || within(com.smiechmateusz.controller.administration.*)")
	public Object BeforeAny(ProceedingJoinPoint pjp) throws Throwable
	{
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		if (ms.getReturnType() == ModelAndView.class)
		{
			System.out.println("Gotcha!");
			ArrayList<String> css = new ArrayList<String>();
			css.add("/media/bootstrap/css/bootstrap.css");
			ArrayList<String> js = new ArrayList<String>();
//			js.add("/media/js/jQuery.js");
//			js.add("/media/bootstrap/js/bootstrap.min.js");
			ModelAndView mav = (ModelAndView) pjp.proceed();
			mav.addObject("css", css);
			mav.addObject("js", js);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!"anonymousUser".equals(auth.getName()))
			{
				mav.addObject("logged", true);
				mav.addObject("user", ((UserDAO) context.getBean("UserDAO")).findByUsername(auth.getName()));
			}
			else
				mav.addObject("logged", false);
			System.out.println(auth.getName());
			MenuDAO menuDAO = (MenuDAO) context.getBean("MenuDAO");
			Criteria c = menuDAO.getCriteria();
			c.add(Property.forName("parent").isNull());
			mav.addObject("menuList", (ArrayList<Menu>) c.list());
			return mav;
		}
		return pjp.proceed();
	}
}
