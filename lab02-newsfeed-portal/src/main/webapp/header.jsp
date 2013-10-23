<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>NewsFeed Portal</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
	</head>
	<body>
		<div id="menu">
		        <p style="text-align: center; font-size: 13pt;"><b>NewsFeed Portal</b></p>
		        <hr/>
		        <c:if test="${!empty sessionScope['user']}">
		        	<p>Welcome, <b><c:out value="${sessionScope['user'].username}"></c:out></b></p>
		        	<hr/>
		        </c:if>
		        
				<a href="/lab02-newsfeed-portal/newsfeed/home">Home</a>
				<hr/>

				<c:if test="${!empty sessionScope['user']}">
					<br/>
					<a href="/lab02-newsfeed-portal/newsfeed/users">People</a>
					<br/>
					<br/>
					<hr/>
					<a href="/lab02-newsfeed-portal/newsfeed/logout">Logout</a>
				</c:if>
				
		</div>
		<div id="content">
