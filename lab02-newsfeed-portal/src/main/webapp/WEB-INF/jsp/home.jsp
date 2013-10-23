<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/><br/>
	
<div id="home_header" align="center">	
	<c:choose>
	<c:when test="${!empty sessionScope['user']}">
		<h1><c:out value="${sessionScope['user'].firstName}"/>&nbsp<c:out value="${sessionScope['user'].lastName}"/></h1>		
		<br/>
		
		<table id="user_main_menu">
			<tr align="center">
				<td><a href="/lab02-newsfeed-portal/newsfeed/home/main">Main</a></td>
				<td><a href="/lab02-newsfeed-portal/newsfeed/home/posts">Posts</a></td>
				<td><a href="/lab02-newsfeed-portal/newsfeed/home/photos">Photos</a></td>
				<td><a href="/lab02-newsfeed-portal/newsfeed/home/shares">Shares</a></td>
			</tr>
		</table>
		<br />
		<br />

		<jsp:include page="${selected_tab}.jsp" />

		</c:when>
	<c:otherwise>
		<h1>Welcome to NewsFeed Portal!</h1>
		<br/>
		<br/>
		<br/>
		<br/>
		<p><b>Please 
			<a style="text-decoration: underline;" href="/lab02-newsfeed-portal/newsfeed/login">login</a> or
			<a style="text-decoration: underline;" href="/lab02-newsfeed-portal/newsfeed/register">register</a>
			to enter portal.</b></p>
		<br/><br/>
	</c:otherwise>
	</c:choose>
	<br/>
</div>	

<%@ include file="/footer.jsp" %>