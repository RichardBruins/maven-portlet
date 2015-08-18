package com.xtivia.search.book;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.RenderRequest;
import javax.xml.namespace.QName;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.EventMapping;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.xtivia.book.Book;



/**
 * Portlet implementation class SearchBookController
 */
@Controller(value = "searchBookController")
@RequestMapping("VIEW")
public class SearchBookController 
{
	@ActionMapping("searchBook")
	public void searchBook(SessionStatus status,ActionRequest request, ActionResponse response)
	{
		System.out.println("Entered the searchbook action");
		String searchBookIsbn = request.getParameter("isbn");
		System.out.println("This is in search book" + searchBookIsbn);	
		SearchBookEvent searchBookEvent = new SearchBookEvent();
		searchBookEvent.setIsbn(searchBookIsbn);
		if(searchBookIsbn != null && !searchBookIsbn.isEmpty())
		{			
			System.out.println("From the searchbookportlet: searchbookisbn is: " + searchBookIsbn);			
			//request.getPortletSession().setAttribute("searchBookIsbn", searchBookIsbn, PortletSession.APPLICATION_SCOPE);
			QName qname = new QName("localhost:8080","searchBookEvent");
	    	response.setEvent(qname, searchBookEvent);
	    	status.setComplete();
		}
		else
		{
			SessionErrors.add(request, "error");
			//response.setRenderParameter("jspPage", "/html/searchbook/view.jsp");
		}
	}
	
	@RequestMapping
    public String render(Model model, SessionStatus sessionStatus,
                    RenderRequest renderRequest) {
    	return "view";
    }
	
	@EventMapping(value ="{localhost:8080}searchBookEvent")
	public void processEvent(EventRequest request, EventResponse response)
	{
		// need this workaround to get event portlet working
		
	}

}
