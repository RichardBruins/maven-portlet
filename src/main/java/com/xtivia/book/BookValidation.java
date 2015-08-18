package com.xtivia.book;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.liferay.portal.kernel.util.Validator;

public class BookValidation extends Validator
{
	public boolean supports(Class clazz) {
        return Book.class.equals(clazz);
    }
	
	public void validateBook(Object obj, Errors e)
	{		
		ValidationUtils.rejectIfEmpty(e, "title", "title.empty");
		ValidationUtils.rejectIfEmpty(e, "author", "author.empty");
		ValidationUtils.rejectIfEmpty(e, "isbn", "isbn.empty");
		ValidationUtils.rejectIfEmpty(e, "summary", "summary.empty");
	}
}
