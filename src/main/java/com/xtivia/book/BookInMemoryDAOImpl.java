package com.xtivia.book;

import java.util.ArrayList;
import java.util.List;

public class BookInMemoryDAOImpl implements BookDAOInterface
{

	private ArrayList <Book> bookList = new ArrayList<Book>();

	public BookInMemoryDAOImpl() 
	{
		
		bookList.add(new Book("The New Yorker Stories", "Ann Beattie","1234", "Story about New York." ));
		bookList.add(new Book("Cleopatra: A Life", "Stacy Schiff","2341", "Story of about a Queen." ));
		bookList.add(new Book("Freedom", "Jonathan Franzen","3412", "A story about the will and fight to survive" ));
		bookList.add(new Book("Room", "Emma Donoghue","4123", "The story about a never ending hall." ));
		bookList.add(new Book("Selected Stories", "William Trevor","7777", "Story about a writer who is deciding on the story about his own life" ));
		
	}

	public ArrayList<Book> getBookList() 
	{
		return bookList;
	}

	public Book getBook(String isbn) 
	{
		
		for(Book book : bookList)
		{
			if(isbn.equals(book.getIsbn()))
			{
				return book;
			}
		}
		return null;
		
	}

	public void editBook(Book book) {
		String isbn = book.getIsbn();
		Book matchingBook = null;
		
		synchronized (bookList) {
			for (Book book_ : bookList) {
				if (book.getIsbn().equals(isbn)) {
					matchingBook = book_;
					break;
				}
			}
			bookList.remove(matchingBook);
			bookList.add(book);
		}
	}

	public void deleteBook(String isbn) 
	{
		List <Book> tmpBookList = new ArrayList<Book>();
		tmpBookList.addAll(bookList);
		
		for(Book book : tmpBookList)
		{
			if(isbn.equals(book.getIsbn()))
			{
				bookList.remove(book);
			}
		}
		
	}

/*	public void updateBookList(Book book) 
	{
		for(Book tempBook : bookList)
		{
			if(tempBook.getIsbn() == book.getIsbn())
			{
				bookList.set(bookList.indexOf(book), book);
			}
		}
		
	}*/


	public void addABook(Book book) 
	{		
		bookList.add(book);
		
	}


	public Book searchForBook(String isbn) {

		for(Book book : bookList)
		{
			if(isbn.equals(book.getIsbn()))
			{
				return book;
			}
		}
		return null;
	}

	public Book makeBook(String title, String author, String isbn, String summary) {
		
		Book newBook = new Book(title, author, isbn, summary);
		return newBook;
	}

	public void setListOfBooks()
	{
		bookList.add(new Book("The New Yorker Stories", "Ann Beattie","1234", "Story about New York." ));
		bookList.add(new Book("Cleopatra: A Life", "Stacy Schiff","2341", "Story of about a Queen." ));
		bookList.add(new Book("Freedom", "Jonathan Franzen","3412", "A story about the will and fight to survive" ));
		bookList.add(new Book("Room", "Emma Donoghue","4123", "The story about a never ending hall." ));
		bookList.add(new Book("Selected Stories", "William Trevor","7777", "Story about a writer who is deciding on the story about his own life" ));
		
	}
/*
	public void updateThisBook(String title, String author, Long isbn,
			String summary) {
		// TODO Auto-generated method stub
		
	}*/


}
