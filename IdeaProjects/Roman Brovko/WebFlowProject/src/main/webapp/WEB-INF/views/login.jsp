<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css"/> ">
    <link rel="stylesheet" type="text/css" href="/resources/dijit/themes/tundra/tundra.css">
    <meta charset="utf-8"/>
</head>
<body class="tundra">
    <form:form method="POST" modelAttribute="user" class="box login">

        <fieldset class="boxBody">

            <span style="float: right">
                <a href="?lang=en"><spring:message code="en" /></a>
                <a href="?lang=ru"><spring:message code="ru" /></a>
			</span>

            <form:label path="name">
                <spring:message code="username" />
            </form:label>
            <form:input path="name" id="username"/>
            <script type="text/javascript">
                Spring.addDecoration(new Spring.ElementDecoration({
                    elementId : "username",
                    widgetType : "dijit.from.ValidationTextBox",
                    widgetAttrs : {
                        promptMessage : "<spring:message code="enter_name"/> "
                    }
                }));
            </script>


            <form:label path="password">
                <spring:message code="password" />
            </form:label>
            <form:input path="password" type="password" id="password"/>
            <script type="text/javascript">
                Spring.addDecoration(new Spring.ElementDecoration({
                    elementId : "password",
                    widgetType : "dijit.from.ValidationTextBox",
                    widgetAttrs : {
                        promptMessage : "<spring:message code="enter_password"/>"
                    }
                }));
            </script>


            <c:if test="${not empty flowRequestContext.messageContext.allMessages}">
                <ul class="red_messages">
                    <c:forEach var="msg" items="${flowRequestContext.messageContext.allMessages}">
                        <li>${msg.text}</li>
                    </c:forEach>
                </ul>
            </c:if>

        </fieldset>

        <footer>
            <a href="${flowExecutionUrl}&_eventId=createUser"><spring:message code="create-user"/></a>

            <c:if test="${not empty errorMessage}">
                <span style="float: right;" class="error">${errorMessage}</span>
            </c:if>
            <input type="submit" class="btnLogin" value="<spring:message code='login'/>" name="_eventId_submit"/>
        </footer>

        <c:if test="${not empty notificationUserCreated}">
            <script type="text/javascript">
                alert("${notificationUserCreated}");
            </script>
        </c:if>

    </form:form>

    <script type="text/javascript" src="/resources/dojo/dojo.js" />
    <script type="text/javascript" src="/resources/spring/Spring.js" />
    <script type="text/javascript" src="/resources/spring/Spring-Dojo.js" />
</body>
</html>
