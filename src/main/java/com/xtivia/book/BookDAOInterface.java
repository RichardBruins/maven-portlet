package com.xtivia.book;

import java.util.ArrayList;

public interface BookDAOInterface 
{
	
	public Book getBook(String isbn);
	
	public ArrayList <Book> getBookList();
	public void deleteBook(String isbn);
	
	public Book searchForBook(String isbn);
	
	public Book makeBook(String title, String author, String isbn,
			String summary);

	void addABook(Book book);

	public void setListOfBooks();
	
	public void editBook(Book book);

	
}
