<%@ include file="/WEB-INF/jsp/maven/init.jsp" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.liferay.portal.kernel.util.UnicodeFormatter"%>

<liferay-ui:error key="error" message="Sorry, All field values are required." />

<portlet:actionURL var="editBookUrl">
	<portlet:param name="myaction" value="editBook" />
</portlet:actionURL>
<portlet:renderURL var="viewUrl">
	<portlet:param name="myaction" value="bookList" />
</portlet:renderURL>

<h1>Edit Book Details</h1>

<table border="1" width="80%">
<form:form name="editBookForm" commandName="book" method="post" action="${editBookUrl}">
<tr>
	<th style="background-color:#00CCFF ">Title</th>
	<td width="90%"><form:input path="title" /></td>				
</tr>
<tr>
	<th style="background-color:#00CCFF ">Author</th>
	<td width="90%"><form:input path="author" /></td>
</tr>
<tr>
	<th style="background-color:#00CCFF ">ISBN</th>
	<td width="90%"><form:input path="isbn" /></td>
</tr>
<tr>
	<th style="background-color:#00CCFF ">Summary</th>
	<td>	<liferay-ui:input-editor name="summary" />
				<script type="text/javascript">
		         function <portlet:namespace />initEditor() {
		          return "<%=UnicodeFormatter.toString(((Book)request.getAttribute("book")).getSummary())%>";
		         }
		       </script> 		
		<form:input path="summary" type="hidden"/></td>
</tr>
<tr>
	<th></th>	
	<td width="90%"><input type="submit" value="Submit Query" style="float: right" /></td>
</tr>

</form:form>
</table>
<a href="<portlet:renderURL/>"><u>cancel</u></a>