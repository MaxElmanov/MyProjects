<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/home.css"/>
    <meta charset="utf-8"/>
</head>
<body>
    <form:form method="POST" modelAttribute="user" class="box login">

        <fieldset class="boxBody">

            <span style="float: right">
                <a href="?lang=en"><spring:message code="en" /></a>
                <a href="?lang=ru"><spring:message code="ru" /></a>
			</span>

            <form:label path="name">
                <spring:message code="username" />
            </form:label>
            <form:input path="name"/>
            <form:errors path="name" cssClass="error"/>

            <form:label path="password">
                <spring:message code="password" />
            </form:label>
            <form:input path="password" type="password"/>

        </fieldset>

        <footer>
            <a href="${flowExecutionUrl}&_eventId=createUser"><spring:message code="create-user"/></a>
            <c:if test="${not empty errorMessage}">
                <span style="float: right;" class="error">${errorMessage}</span>
            </c:if>
            <br>
            <input type="submit" class="btnLogin" value="<spring:message code='login'/>" name="_eventId_submit"/>
        </footer>

        <c:if test="${not empty notificationUserCreated}">
            <script type="text/javascript">
                alert("${notificationUserCreated}");
            </script>
        </c:if>

    </form:form>
</body>
</html>
