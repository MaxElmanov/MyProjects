<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
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
            <a href="${flowExecutionUrl}&_eventId=createUser">Create user</a>
            <c:if test="${not empty errorMessage}">
                <span style="float: right;" class="error">${errorMessage}</span>
            </c:if>
            <input type="submit" class="btnLogin" value="Login" name="_eventId_submit"/>
        </footer>

        <c:if test="${not empty notificationUserCreated}">
            <script type="text/javascript">
                alert("${notificationUserCreated}");
            </script>
        </c:if>

    </form:form>
</body>
</html>
