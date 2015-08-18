package com.xtivia.search.book;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement
public class SearchBookEvent implements Serializable 
{
	private static final long serialVersionUID = 3L;

	private String isbn;

	public SearchBookEvent(){}
	
	public SearchBookEvent(String isbn)
	{
		this.isbn = isbn;
	}
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
