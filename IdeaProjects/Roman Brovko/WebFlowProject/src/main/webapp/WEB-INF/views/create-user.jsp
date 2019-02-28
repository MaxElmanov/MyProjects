<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Create user</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/home.css"/>
    <meta charset="utf-8"/>
</head>
<body>
    <form:form method="POST" modelAttribute="user" class="box login">

        <fieldset class="boxBody">

            <span style="float: right;">
                <a href="?lang=en"><spring:message code="en"/></a>
                <a href="?lang=ru"><spring:message code="ru"/></a>
            </span>

            <form:label path="name">
                <spring:message code="username"/>
            </form:label>
            <form:input path="name"/>
            <form:errors path="name" cssClass="error"/>

            <form:label path="password">
                <spring:message code="password"/>
            </form:label>
            <form:input path="password" type="password"/>

        </fieldset>

        <footer>
            <%--<c:if test="${not empty messageSuchUserExistsYet}">--%>
                <%--<span style="float: right;" class="error">${messageSuchUserExistsYet}</span>--%>
            <%--</c:if>--%>

            <%--<c:if test="${not empty messageInvalidFields}">--%>
                <%--<span style="float: right;" class="error">${messageInvalidFields}</span>--%>
            <%--</c:if>--%>

            <c:if test="${not empty flowRequestContext.messageContext.allMessages}">
                <ul class="red_messages">
                    <c:forEach var="msg" items="${flowRequestContext.messageContext.allMessages}">
                        <li>${msg.text}</li>
                    </c:forEach>
                </ul>
            </c:if>

            <br/>
            <input type="submit" class="btnLogin" value="<spring:message code="create-user"/> " name="_eventId_submit"/>
            <input type="submit" class="btnLogin" value="<spring:message code="cancel"/>" name="_eventId_cancel"/>
        </footer>

    </form:form>
</body>
</html>
