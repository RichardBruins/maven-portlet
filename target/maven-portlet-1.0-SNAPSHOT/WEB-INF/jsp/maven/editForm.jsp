<%@ include file="/WEB-INF/jsp/maven/init.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean class="java.lang.String" id="welcomeMessageURL" scope="request" />

<portlet:defineObjects />

<portlet:actionURL var="editMessageUrl">
	<portlet:param name="myaction" value="editMessage" />
</portlet:actionURL>
<form:form name="editForm" commandName="message" method="post" action="${editMessageUrl}" >
<table>
<tr>
<td>Please type the contents of your welcome message:</td>
<!-- <td><input type="text" name="welcomeMessage"></td> -->
<td><form:input path="welcomeMessage" /></td>
</tr>
</table>
<input type="submit" value="Edit Preferences" />
</form:form>