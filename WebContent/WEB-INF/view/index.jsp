<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!--
<h1>Welcome</h1>
<table>
<sec:authorize access="isAuthenticated()">
        <div class="ui segment">
        User: <sec:authentication property="principal.username" />, Role: <sec:authentication property="principal.authorities"/>
        </div>
       	<h2>${message}</h2>
	
	<form action="/logout" method="post">
		<input value="Logout" type="submit">
	</form>
</sec:authorize>
</table>
<a href="<c:url value="/login/home"></c:url>">Show Home Page</a>  -->
<div id="ui segment">

	<h3>Welcome</h3>
	<table class="ui celled  striped table">
		<tr>${message}
	 		User: <sec:authentication property="principal.username" />, Role: <sec:authentication property="principal.authorities"/>
		<tr>	
			<br><br>
		<tr>	<form action="${pageContext.request.contextPath}/firstPage" method="get">
			<input value="Συνέχεια" type="submit">
			</form>
		</tr>
	</table>
</div>	
	