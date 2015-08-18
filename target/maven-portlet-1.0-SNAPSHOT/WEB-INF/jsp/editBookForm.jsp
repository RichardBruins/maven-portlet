<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<portlet:actionURL var="editBookActionUrl">
	<portlet:param name="myaction" value="editBook" />
</portlet:actionURL>
<portlet:renderURL var="viewUrl">
	<portlet:param name="myaction" value="bookList" />
</portlet:renderURL>
<form:form name="editBookForm" commandName="book" method="post"
	action="${editBookActionUrl}">
	<table>
		<tr align="left">
			<a href="${viewUrl}">Home</a>
		</tr>
	</table>
	<table>
		<tr>
			<td>Title:<font style="color: #C11B17;">*</font></td>
			<td><form:input path="title" /></td>
			<td><font style="color: #C11B17;"><form:errors
				path="title" /></font></td>
		</tr>
		<tr>
			<td>Author:<font style="color: #C11B17;">*</font></td>
			<td><form:input path="author" /></td>
			<td><font style="color: #C11B17;"><form:errors
				path="author" /></font></td>
		</tr>
		<tr>
			<td>ISBN:<font style="color: #C11B17;">*</font></td>
			<td><form:input path="isbnNumber" /></td>
			<td><font style="color: #C11B17;"><form:errors
				path="isbnNumber" /></font></td>
		</tr>
		<tr>
			<td>Summary:<font style="color: #C11B17;">*</font></td>
			<td><form:input path="summary" /></td>
			<td><font style="color: #C11B17;"><form:errors
				path="summary" /></font></td>
		</tr>
	</table>
	<table align="right">
		<tr>
			<td><input type="submit" value="Update Book" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>
<br></br>