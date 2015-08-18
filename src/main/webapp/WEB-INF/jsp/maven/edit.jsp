<%@ include file="/WEB-INF/jsp/maven/init.jsp" %>

<jsp:useBean class="java.lang.String" id="welcomeMessageURL" scope="request" />

<portlet:defineObjects />

<portlet:actionURL var="editWelcomeMessageUrl" name="editWelcomeMessage"></portlet:actionURL>

<form
id="<portlet:namespace />welcomeMessageform"
action="${editWelcomeMessageUrl}"
method="post">

<table>
<tr>
<td>Please type the contents of your welcome message:</td>
<td><input type="text" name="welcomeMessage" value = "${requestScope.welcomeMessage}"></td>
</tr>
</table>
<input type="submit" id="welcomeMessage" title="Edit Preferences" value="Edit Preferences">
</form>