<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="user_posts">

	<form action="/lab02-newsfeed-portal/newsfeed/post" method="post">
		<input type="text" name="text" size="80"><input type="submit" value="Post">
	</form>

	<br/><br/>

	<c:choose>
		<c:when test="${!empty posts}">
			<div id="posts" align="center">			
				<table class="default_table">				
					<c:forEach var="post" items="${posts}">
						<tr>
							<td width="200px" align="center"><c:out value="${post.formattedDate}" /></td>
							<td width="400px"><b><c:out value="${post.text}" /></b></td>	
						</tr>
					</c:forEach>
				</table>
				<br /><br />
			</div>
		</c:when>
		<c:otherwise>
			<h2>You have no posts</h2>	
		</c:otherwise>
	</c:choose>
</div>