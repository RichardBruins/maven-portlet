<%@ include file="init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<portlet:defineObjects />
<liferay-ui:success key="configuration-saved" message="Your request processed successfully" />
Display Options:
<br/><br/> 

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">

	
	<aui:input name="title" type="checkbox"  value = "${requestScope.title}"/>
	
	<aui:input name="author" type="checkbox"  value = "${requestScope.author}"/>
	
	<aui:input name="isbn" type="checkbox"  value = "${requestScope.isbn}"/>
	
	<aui:input name="summary" type="checkbox"  value ="${requestScope.summary}"/>

    <aui:button-row>
       <aui:button type="submit" />
    </aui:button-row>
</aui:form>
