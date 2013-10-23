<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<div align="center">

		<h3>Registration</h3>
		
		<!-- Spring MVC form -->
		<form:form commandName="user">
			<table cellspacing="0" cellpadding="5" id="register_form">
				<tr>
					<td colspan="2" align="center">
						<spring:hasBindErrors name="user">
							<form:errors path="*" cssStyle="color: red" />
						</spring:hasBindErrors>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td nowrap><label for="username">Username:</label></td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td nowrap><label for="password">Password:</label></td>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<td nowrap><label for="passwordConfirm">Confirm Password:</label></td>
					<td><form:password path="passwordConfirm" /></td>
				</tr>
				<tr>
					<td nowrap><label for="firstName">First Name:</label></td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td nowrap><label for="lastName">Last Name:</label></td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td nowrap><label for="googleId">Google ID:</label></td>
					<td><form:input path="googleId" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Register" />
					</td>
				</tr>
			</table>
		</form:form>
		
		<a href="/lab02-newsfeed-portal/newsfeed/login"><b>Login</b></a><br/>		
</div>

<%@ include file="/footer.jsp" %>