<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Create user</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/home.css"/>
</head>
<body>
    <form:form method="POST" modelAttribute="user" class="box login">

        <fieldset class="boxBody">

            <form:label path="name">
                Username
            </form:label>
            <form:input path="name"/>
            <form:errors path="name" cssClass="error"/>

            <form:label path="password">
                Password
            </form:label>
            <form:input path="password" type="password"/>

        </fieldset>

        <footer>
            <c:if test="${not empty messageSuchUserExistsYet}">
                <span style="float: right;" class="error">${messageSuchUserExistsYet}</span>
            </c:if>

            <c:if test="${not empty messageInvalidFields}">
                <span style="float: right;" class="error">${messageInvalidFields}</span>
            </c:if>
            <br/>
            <input type="submit" class="btnLogin" value="Create" name="_eventId_submit"/>
            <input type="submit" class="btnLogin" value="Cancel" name="_eventId_cancel"/>
        </footer>

    </form:form>
</body>
</html>
