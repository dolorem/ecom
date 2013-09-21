package com.smiechmateusz.model.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.smiechmateusz.dao.MenuDAO;
import com.smiechmateusz.model.Menu;

/**
 * Class validating MenuFormModel.
 * 
 * @see com.smiechmateusz.model.form.MenuFormModel
 * @author Śmiech Mateusz
 */
public class MenuValidator implements Validator
{
	
	/** The menu dao. */
	@Autowired
	MenuDAO menuDAO;
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> arg0)
	{
		return MenuFormModel.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "MenuFormModel.emptyDescription");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "link", "MenuFormModel.emptyLink");
		MenuFormModel model = (MenuFormModel) obj;
		Menu original = (Menu) menuDAO.getById(model.getId());
		if (original != null && model.gonnaBeSelfChild(original))
			errors.rejectValue("parentId", "MenuFormModel.selfChild");
	}
}
