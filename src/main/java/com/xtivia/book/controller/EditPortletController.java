package com.xtivia.book.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.xtivia.book.Book;
import com.xtivia.book.Message;

@Controller(value = "editPortletController")
@RequestMapping("EDIT")
public class EditPortletController 
{

		@RenderMapping
	    public String editForm(Model model, PortletPreferences portletPreferences, RenderRequest renderRequest, RenderResponse renderResponse)
	     {
		
			PortletURL welcomeMessageURL = renderResponse.createActionURL();
			welcomeMessageURL.setParameter("welcomeMessage", "welcomeMessage");
			renderRequest.setAttribute("welcomeMessageURL", welcomeMessageURL.toString());
			String welcomeMessage = (String) portletPreferences.getValue("welcomeMessage", "default");
			if (welcomeMessage.equalsIgnoreCase("default")) {
				 welcomeMessage = "Personalizable welcome message, please click on Edit Preferences to edit this message";
			 }
			System.out.println("This is in the render action/ editform");
				model.addAttribute("welcomeMessage", welcomeMessage);
		        return "edit";
	     }
	
		@ActionMapping
	    public void editMessage(@ModelAttribute(value = "message") Message message,
	    		ActionRequest actionRequest, ActionResponse actionResponse) throws ReadOnlyException, ValidatorException, IOException, PortletModeException
	    {
		System.out.println("This is in the process action/ editMesage");
		String welcomeMessage = actionRequest.getParameter("welcomeMessage");
		if (welcomeMessage != null) {
			PortletPreferences prefs = actionRequest.getPreferences();
			prefs.setValue("welcomeMessage", actionRequest.getParameter("welcomeMessage"));
			prefs.store();
			actionResponse.setPortletMode(PortletMode.VIEW);
		}
	    }
		@ModelAttribute("message")
		public Message getCommandObject() {
			return new Message();
		}
	
}
