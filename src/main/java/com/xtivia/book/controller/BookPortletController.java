package com.xtivia.book.controller;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.EventMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.xtivia.book.Book;
import com.xtivia.book.BookManager;
import com.xtivia.search.book.SearchBookEvent;


/**
 * Portlet implementation class BookPortletController
 */
@RequestMapping(value="VIEW")
@Controller(value = "bookPortletController")
public class BookPortletController
{			
	@Autowired
	@Qualifier("bookManager")	
	private BookManager bookManager;  
	
	//model.addAttribute("book", book);
	
	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
	}
		
	@RenderMapping
	public String showBooks(RenderRequest renderRequest, RenderResponse renderResponse) 
	{
		PortletPreferences portletPreferences = renderRequest.getPreferences();
		String title = portletPreferences.getValue("title", "");
		String author = portletPreferences.getValue("author", "");
		String bookIsbn = portletPreferences.getValue("isbn", "");
		String summary = portletPreferences.getValue("summary", "");
		
		if(!title.isEmpty() && Boolean.parseBoolean(title))
		{
			renderRequest.setAttribute("title", true);
		}
		
		if(!author.isEmpty() && Boolean.parseBoolean(author))
		{
			renderRequest.setAttribute("author", true);		
		}
		
		if(!bookIsbn.isEmpty() && Boolean.parseBoolean(bookIsbn))
		{
			renderRequest.setAttribute("isbn", true);		
		}
		
		if(!summary.isEmpty() && Boolean.parseBoolean(summary))
		{
			renderRequest.setAttribute("summary", true);
		}
		
		String welcomeMessage = (String) portletPreferences.getValue("welcomeMessage", "Personalizable welcome message, please click on Edit Preferences to edit this message");					 
		renderRequest.setAttribute("welcomeMessage", welcomeMessage);
		renderRequest.setAttribute("bookList", bookManager.getBooks());
		
		return "view";
	}	

	@ModelAttribute("book")
	public Book getCommandObject(PortletRequest portletRequest) 
	{
		String action = portletRequest.getParameter("myaction");		
		String isbn = portletRequest.getParameter("isbn");
		if(action != null && action.equals("editBook"))
		{
			Book book = bookManager.getBook(isbn);
						
			book.setAuthor((portletRequest.getParameter("author")));
			book.setIsbn((portletRequest.getParameter("isbn")));
			book.setTitle((portletRequest.getParameter("title")));
			book.setSummary((portletRequest.getParameter("summary")));
			
			return book;			
		}
		else if(action!=null && (action.equals("details") || action.equals("editBookForm")))
		{
			return bookManager.getBook(isbn);
		}
				return new Book();
	}
	
/*	@ModelAttribute("book")
	public Book getBook(@RequestParam Long isbn) {
		return bookManager.getBook(isbn);
	}*/
	
	
/*	@ActionMapping(params = "myaction=delete")
	public void delete(String isbn,ActionRequest actionRequest)
	{
		bookManager.deleteBook(isbn);
				
	}*/
	
	@RenderMapping(params = "myaction=addBookForm")
	public String addBookForm(RenderResponse response) 
	{
		return "addBookForm";
	}
	
	@ActionMapping(params = "myaction=addBook")
	public void addBook(@ModelAttribute(value = "book") Book book, ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus status)
	{


		if((!book.getTitle().isEmpty()) && (!book.getAuthor().isEmpty()) && (!book.getIsbn().isEmpty()) && 
				(!book.getSummary().isEmpty()))
		{
			bookManager.addBook(book);			
			//actionResponse.setRenderParameter("myaction", "books");
			// --set the session status as complete to cleanup the model
			// attributes
			// --stored using @SessionAttributes, otherwise when you click
			// --'Add Book' button you'll see the book information pre-populated
			// -- because the getCommandObject method of the controller is not
			// --invoked
			//status.setComplete();
		} else 
		{
			SessionErrors.add(actionRequest, "error");
			actionResponse.setRenderParameter("myaction", "addBookForm");
		}
	}
	
	@RenderMapping(params = "myaction=details")
	public String details(@ModelAttribute("book") Book book,RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortalException 
	{             
        return "details";
    }
	
	@RenderMapping(params="myaction=editBookForm")
	public String editBookForm(@ModelAttribute("book") Book book, RenderRequest renderRequest, RenderResponse renderResponse) 
	{
		
		return "editBookForm";
	}
	
	@ActionMapping(params="myaction=editBook")
	public void editBook(@ModelAttribute("book") Book book, ActionRequest actionRequest, ActionResponse actionResponse, 
			SessionStatus status,ModelMap modelMap)  
	{		
			if((!book.getTitle().isEmpty()) && (!book.getAuthor().isEmpty()) && (!book.getIsbn().isEmpty()) && 
					(!book.getSummary().isEmpty()))
			{
				bookManager.editBook(book);			
				actionRequest.setAttribute("success", true);
				SessionMessages.add(actionRequest, "success");
				//status.setComplete();
			} else 
			{
				SessionErrors.add(actionRequest, "error");
				//modelMap.put(BindingResult.class.getName() + ".book", bindingResult);
				actionResponse.setRenderParameter("myaction", "editBookForm");
			}	
	}
	
	@ResourceMapping(value="deleteBook")	
	public void deleteBook(ResourceRequest request, ResourceResponse response)
	{
		String isbn = request.getParameter("isbn");
		bookManager.deleteBook(isbn);				
	}	
	
	@ExceptionHandler({ Exception.class })
	public String handleException() {
		return "errorPage";
	}
	
	@EventMapping(value ="{localhost:8080}searchBookEvent")
	public void processEvent(@ModelAttribute("book") Book book,EventRequest request, EventResponse response)
	{
		//check both cases with model attri and without ...include modelmap and without..
		//  it is looking for an event phase with an empty map
		Event event = request.getEvent();
		//String bookIsbn = (String)event.getValue();
		// One of these getvalues should work...
		SearchBookEvent searchBookEvent = (SearchBookEvent)event.getValue();
				
		String bookIsbn = searchBookEvent.getIsbn();
		//response.setRenderParameter("myaction", "details");  //all book values are good
		
		response.setRenderParameter("isbn", bookIsbn);
		Book tempBook = bookManager.getBook(bookIsbn);
		book.setTitle(tempBook.getTitle());
		book.setAuthor(tempBook.getAuthor());
		book.setIsbn(tempBook.getIsbn());
		book.setSummary(tempBook.getSummary());
		
		response.setRenderParameter("title", book.getTitle());
		response.setRenderParameter("author", book.getAuthor());
		response.setRenderParameter("summary", book.getSummary());
		//response.setRenderParameter("book", book);
		//request.setAttribute("book",book);
		response.setRenderParameter("myaction", "details");
		
		
	}
}
