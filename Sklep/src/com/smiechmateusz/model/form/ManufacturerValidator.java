package com.smiechmateusz.model.form;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Class validating ManufacturerFormModel.
 * 
 * @see com.smiechmateusz.model.form.ManufacturerFormModel
 * @author Śmiech Mateusz
 */
public class ManufacturerValidator implements Validator
{
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0)
	{
		return ManufacturerFormModel.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "ManufacturerFormModel.emptyName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "ManufacturerFormModel.emptyDescription");
	}
}
