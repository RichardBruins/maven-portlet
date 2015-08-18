<%@ include file="/WEB-INF/jsp/maven/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="com.xtivia.book.Book" %>
<%@ page import ="com.xtivia.book.BookInMemoryDAOImpl" %>
<%@ page import ="com.xtivia.book.BookManager" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<html>
<portlet:defineObjects />
<style>
#div1 {
    white-space: nowrap; 
    width: 11em; 
    overflow: hidden;
    text-overflow: ellipsis; 
    
}
</style>

<portlet:renderURL var="addBookURL">
	<portlet:param name="myaction" value="addBookForm"/>
</portlet:renderURL>

<jsp:useBean id="welcomeMessage" class="java.lang.String" scope="request" />

<p><%= welcomeMessage %></p>

<script>
function deleteBook(deleteBookUrl, rowId){
	if(confirm("Are you sure that you want to remove the Item?")) {
		$.ajax(
		{			
			type: "POST",
			url: deleteBookUrl,
			cache: false,
			success: function( data ) {
			$('#' + rowId).hide();
			}
		}
	);
	}
	}</script>
	
	
<table border="1" width="80%" class="td1" >

<tr>    

			<c:if test="${requestScope.title== 'true'}">
			<th style="background-color:#00CCFF ">Book Title</th>
			</c:if>
			<c:if test="${requestScope.author== 'true'}">	
			<th style="background-color:#00CCFF ">Author</th>
			</c:if>
			<c:if test="${requestScope.isbn== 'true'}">
			<th style="background-color:#00CCFF ">ISBN</th>
			</c:if>
			<c:if test="${requestScope.summary== 'true'}">
			<th style="background-color:#00CCFF ">Summary</th>
			</c:if>
			<th style="background-color:#00CCFF ">Delete Action</th>
</tr>



<c:forEach var="book" items="${requestScope.bookList }">
	<portlet:renderURL var="detailsURL">
	    <portlet:param name="isbn" value="${book.isbn}"/>	    	 
	    <portlet:param name="myaction" value="details"/>	    
	</portlet:renderURL>

	<portlet:resourceURL var="deleteURL" id ="deleteBook">
	    <portlet:param name="isbn" value="${book.isbn}"/>
	</portlet:resourceURL>
	<tr id="${book.isbn}">
		<c:if test="${requestScope.title== 'true'}">
			<td> <a href="${detailsURL}"> ${book.title} </a> </td>
		</c:if>
		<c:if test="${requestScope.author== 'true'}">
			<td>${book.author}</td>
		</c:if>
		<c:if test="${requestScope.isbn== 'true'}">
			<td>${book.isbn}</td>
		</c:if>
		<c:if test="${requestScope.summary== 'true'}">
			<td><div id="div1">${book.summary}</div></td>
		</c:if>
		<td> <a href="javascript:void(0);" onclick= "deleteBook('${deleteURL}' , '${book.isbn}')">Delete Book</a> 		
	</tr>
			
</c:forEach>

 
</table>
<br/><a href="<%= addBookURL %>"><u>add</u></a>
</html>
