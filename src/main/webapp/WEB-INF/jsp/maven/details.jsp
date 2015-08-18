<%@ include file="/WEB-INF/jsp/maven/init.jsp" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<h1>Book Details</h1>
<table border="1" width="80%">
<tr >

<th style="background-color:#00CCFF "> Book Title</th>



<td>${requestScope.book.title}</td>
</tr>

<tr>
<th style="background-color:#00CCFF ">Author</th>
<td>${requestScope.book.author}</td>
</tr>

<tr>
<th style="background-color:#00CCFF ">ISBN</th>
<td>${requestScope.book.isbn}</td>
</tr>

<tr>
<th style="background-color:#00CCFF ">Summary</th>
<td>${requestScope.book.summary}</td>
</tr>

</table>
<a href="<portlet:renderURL/>"><u>back</u></a>

<a href="<portlet:renderURL>
			<portlet:param name="myaction" value="editBookForm" />
			<portlet:param name="isbn" value="${requestScope.book.isbn}" />			
		 </portlet:renderURL>
		"><u>edit</u></a>