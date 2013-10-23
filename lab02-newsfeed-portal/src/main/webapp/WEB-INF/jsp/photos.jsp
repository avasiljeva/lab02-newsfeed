<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="user_photos">

	<h3>Add new photo</h3>
	<form:form commandName="fileUpload" action="/lab02-newsfeed-portal/newsfeed/photo" enctype="multipart/form-data">
		<table id="photo_form">
			<tr>
				<td colspan="2">
				<spring:hasBindErrors name="fileUpload">
					<form:errors path="*" cssStyle="color: red" />
				</spring:hasBindErrors></td>
			</tr>
			<tr>
				<td><label for="file">Photo</label>:</td>
				<td><form:input type="file" path="file" /></td>
			</tr>
			<tr>
				<td><label for="comment">Comment</label>:</td>
				<td><form:input path="comment" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit"value="Upload" />
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<c:choose>
		<c:when test="${!empty photos}">
			<div id="photos" align="center">			
				<table class="default_table">				
					<c:forEach var="photo" items="${photos}">
						<tr><td align="center">
							<img src="/lab02-newsfeed-portal/images/<c:out value="${photo.src}"/>" width="600">
						</td></tr>
						<tr><td>
							<c:out value="${photo.comment}" />
							<br/><br/>
						</td></tr>
					</c:forEach>
				</table>
				<br /><br />
			</div>
		</c:when>
		<c:otherwise>
			<h2>You have no photos</h2>	
		</c:otherwise>
	</c:choose>
</div>