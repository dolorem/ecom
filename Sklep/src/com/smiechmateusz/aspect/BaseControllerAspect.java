package com.smiechmateusz.aspect;

import java.util.ArrayList;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.dao.MenuDAO;
import com.smiechmateusz.dao.UserDAO;
import com.smiechmateusz.model.Menu;

/**
 * Class containing advices serving as servlet utils which should be executed before/after multiple controllers.
 * 
 * @author Śmiech Mateusz
 */
@Aspect
public class BaseControllerAspect implements ApplicationContextAware
{
	
	/** The context. */
	ApplicationContext context;
	
	/* ApplicationContextAware implementation
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * @param context Spring Application Context
	 */
	public void setApplicationContext(ApplicationContext context)
	{
		this.context = context;
	}
	
	/**
	 * Advice executed before any controller. Sets up default CSS and JS, handles user bean and populates menu.
	 * 
	 * @param pjp ProceedingJoinPoint injected by Spring AOP            
	 * @return ModelAndView if method return type was ModelAndView, pjp.proceed() otherwise
	 * @throws Throwable required by @Around annotation
	 */
	@Around("within(com.smiechmateusz.controller.*) || within(com.smiechmateusz.controller.administration.*)")
	public Object BeforeAny(ProceedingJoinPoint pjp) throws Throwable
	{
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		if (ms.getReturnType() == ModelAndView.class)
		{
			ModelAndView mav = (ModelAndView) pjp.proceed();
			initializeStaticResources(mav);
			initializeUserBean(mav);
			initializeMenu(mav);
			return mav;
		}
		return pjp.proceed();
	}

	/**
	 * Initializes menu bean and populates it from the database.
	 * 
	 * @param mav ModelAndView representing page which will be rendered
	 */
	private void initializeMenu(ModelAndView mav)
	{
		MenuDAO menuDAO = (MenuDAO) context.getBean("MenuDAO");
		Criteria c = menuDAO.getCriteria();
		c.add(Property.forName("parent").isNull());
		mav.addObject("menuList", (ArrayList<Menu>) c.list());
	}

	/**
	 * Handles authentication and initializes user bean for further usage in JSP.
	 * 
	 * @param mav ModelAndView representing page which will be rendered
	 */
	private void initializeUserBean(ModelAndView mav)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(auth.getName()))
		{
			mav.addObject("logged", true);
			mav.addObject("user", ((UserDAO) context.getBean("UserDAO")).findByUsername(auth.getName()));
		}
		else
			mav.addObject("logged", false);
	}

	/**
	 * Initializes static resources like stylesheets or scripts 
	 * which should be always present and will be later parsed in <head>.
	 * 
	 * @param mav ModelAndView representing page which will be rendered
	 */
	private void initializeStaticResources(ModelAndView mav)
	{
		ArrayList<String> css = new ArrayList<String>();
		css.add("/media/bootstrap/css/bootstrap.css");
		ArrayList<String> js = new ArrayList<String>();
		mav.addObject("css", css);
		mav.addObject("js", js);
	}
}
