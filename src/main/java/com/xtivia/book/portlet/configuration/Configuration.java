package com.xtivia.book.portlet.configuration;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

public class Configuration extends DefaultConfigurationAction  
{
	
	@Override
	public String render(PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception 
	{	
		
		String portletResource = ParamUtil.getString(renderRequest,"portletResource");
        PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest, portletResource);
                
		String title = portletPreferences.getValue("title", "");
		String author = portletPreferences.getValue("author", "");
		String isbn = portletPreferences.getValue("isbn", "");
		String summary = portletPreferences.getValue("summary", "");
		
		if(!title.isEmpty() && Boolean.parseBoolean(title)){
			renderRequest.setAttribute("title", true);
		}
		
		if(!author.isEmpty() && Boolean.parseBoolean(author)){
			renderRequest.setAttribute("author", true);		
		}
		
		if(!isbn.isEmpty() && Boolean.parseBoolean(isbn)){
			renderRequest.setAttribute("isbn", true);		
		}
		
		if(!summary.isEmpty() && Boolean.parseBoolean(summary)){
			renderRequest.setAttribute("summary", true);
		}
		 return "/WEB-INF/jsp/maven/configuration.jsp";
	}	
			
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception 
	{ 
		
		
		
		String portletResource = ParamUtil.getString(actionRequest,"portletResource");
        PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
        SessionMessages.add(actionRequest,portletConfig.getPortletName() +SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,portletResource);  
	      String title = ParamUtil.getString(actionRequest, "title"); 
		 
		  String author = ParamUtil.getString(actionRequest, "author");
		
		  String isbn = ParamUtil.getString(actionRequest, "isbn"); 
	
		  String summary = ParamUtil.getString(actionRequest, "summary"); 		
		  
		
		  
		if(title != null && !title.isEmpty() && Boolean.parseBoolean(title)){
			  
			portletPreferences.setValue("title", title);
		  }else{
			  portletPreferences.setValue("title", "");
		  }
		
		  
		if(author != null && !author.isEmpty() && Boolean.parseBoolean(author)){
					  
			portletPreferences.setValue("author", author);
				  }else{
					  portletPreferences.setValue("author", "");
				  }
		
		if(isbn != null && !isbn.isEmpty() && Boolean.parseBoolean(isbn)){
			  
			portletPreferences.setValue("isbn", isbn);
		
		}else{
			portletPreferences.setValue("isbn", "");
		}
		
		if(summary != null && !summary.isEmpty() && Boolean.parseBoolean(summary)){
			  
			portletPreferences.setValue("summary", summary);
		
		}else{
			portletPreferences.setValue("summary", "");
		}
			
		 if ( SessionErrors.isEmpty( actionRequest ) )
					{
			 		portletPreferences.store();
			 		SessionMessages.add(actionRequest, "configuration-saved");
				    SessionMessages.add( actionRequest, portletConfig.getPortletName()
								+ ".doConfigure" );
					}
				
	}


}
