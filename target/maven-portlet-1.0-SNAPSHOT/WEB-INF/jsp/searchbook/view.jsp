<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ include file="/WEB-INF/jsp/maven/init.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="com.xtivia.book.Book" %>
<%@ page import ="com.xtivia.book.BookInMemoryDAOImpl" %>
<%@ page import ="com.xtivia.book.BookManager" %>

<portlet:defineObjects />
<liferay-ui:error key="error" message="An ISBN is required for a search." />

<portlet:actionURL var="searchBookURL" name="searchBook"> 
</portlet:actionURL>

<b>Search For a Book by ISBN</b>
<table border="1" width="80%">
<form name="fm" method="POST" action="${searchBookURL}" onsubmit="return validateForm()">
<tr>
	<th style="background-color:#00CCFF ">ISBN</th>
	<td>
		<input name="<portlet:namespace />isbn" label="ISBN" value="${book.isbn}"/>
		<input type="submit" value="Search" />
	</td>	
</tr>
</tr>	
		
		
</form>
</table>