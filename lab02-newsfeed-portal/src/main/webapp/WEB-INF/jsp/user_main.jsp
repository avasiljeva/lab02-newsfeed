<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="user_profile">
	<b>Google ID:</b>
	<c:out value="${sessionScope['user'].googleId}" />
</div>
<br />
<br />
<div id="user_feeds">User feeds are going to appear there...</div>
