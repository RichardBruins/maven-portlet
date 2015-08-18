package com.xtivia.book;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service("bookManager")
public class BookManager 
{
	private BookInMemoryDAOImpl bookDAO;

	public BookManager()
	{
		bookDAO = new BookInMemoryDAOImpl();
	}
	
	public ArrayList <Book> getBooks()
	{
		
		return bookDAO.getBookList();
		
	}
	
	public Book getBook(String isbn)
	{
		
		return bookDAO.getBook(isbn);
		
	}
	
	public void deleteBook(String isbn)
	{
		bookDAO.deleteBook(isbn);
	}
	
	public void addBook(Book book)
	{
		bookDAO.addABook(book);
	}
	
/*	public Book makeBook(String title, String author, Long isbn, String summary)
	{
		return new Book (title, author, isbn, summary);
		
	}*/
	
	public Book makeBook(String title, String author, String isbn, String summary)
	{
		return new Book (title, author, isbn, summary);
		
	}
	public Book searchForBook(String isbn)
	{		
		return bookDAO.searchForBook(isbn);
		
	}
	
/*	public void updateThisBook(String title, String author, Long isbn, String summary)
	{
		bookDAO.updateThisBook(title, author, isbn, summary);
	}*/
	
	public void setBookList()
	{
		bookDAO.setListOfBooks();
	}
	
	public void editBook(Book book)
	{
		bookDAO.editBook(book);
	}


}
