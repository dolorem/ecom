package com.smiechmateusz.model.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.smiechmateusz.dao.CategoryDAO;
import com.smiechmateusz.model.Category;

/**
 * Class validating CategoryFormModel.
 * 
 * @see com.smiechmateusz.model.form.CategoryFormModel
 * @author Śmiech Mateusz
 */
public class CategoryValidator implements Validator
{
	
	/** The category dao. */
	@Autowired
	private CategoryDAO categoryDAO;
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0)
	{
		return CategoryFormModel.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors)
	{
		CategoryFormModel model = (CategoryFormModel) obj;
		Category original = (Category) categoryDAO.getById(model.getId());
		if (original != null && model.gonnaBeSelfChild(original))
			errors.rejectValue("parentId", "CategoryFormModel.selfChild");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "CategoryFormModel.emptyName");	
		
	}
}
