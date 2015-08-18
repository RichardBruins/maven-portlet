package com.xtivia.book.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.xtivia.book.*;

public class BookValidation  implements Validator
{

	public boolean supports(Class clazz) 
	{
		
		return Book.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) 
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
				"required.title", "Book title is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author",
				"required.author", "Book author is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn",
				"required.isbn", "Book isbn is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "summary",
				"required.summary", "Book summary is required.");
	 
		
		
	}

}
