<%@ include file="/WEB-INF/jsp/maven/init.jsp" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<liferay-ui:error key="error" message="Sorry, All field values are required." />

<portlet:actionURL var="addBookUrl">
	<portlet:param name="myaction" value="addBook" />
</portlet:actionURL>

<h1>Add A Book</h1>

<table border="1" width="80%">
<form:form name="addBookForm" commandName="book" method="post" action="${addBookUrl}">
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
	<td><liferay-ui:input-editor name="summary" />
		<form:input path="summary" type="hidden"/></td>
</tr>
<tr>
	<th></th>	
	<td width="90%"><input type="submit" value="Add" style="float: right"/></td>
</tr>

</form:form>
</table>
	
<a href="<portlet:renderURL/>"><u>cancel</u></a>
