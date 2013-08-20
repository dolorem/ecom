package com.smiechmateusz.model.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Class validating ArticleFormModel.
 * 
 * @see com.smiechmateusz.model.form.ArticleFormModel
 * @author Śmiech Mateusz
 */
@Component
public class ArticleValidator implements Validator
{

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0)
	{
		return ArticleFormModel.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "ArticleFormModel.emptyName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "ArticleFormModel.emptyDescription");
	}
}
