package com.smiechmateusz.model.form;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.smiechmateusz.model.Site;

public class SiteValidator implements Validator
{
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> arg0)
	{
		return Site.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors)
	{
		Site s = (Site) obj;
		if (!s.getPath().startsWith("/"))
			errors.rejectValue("path", "Site.pathNotAbsolute");
		else if (!s.getPath().endsWith(".htm"))
			errors.rejectValue("path", "Site.pathExtensionInvalid");
	}	
}
