<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>resources/style.css"/>
</head>
<body>

    <form name="form_login" action="<%=request.getContextPath()%>/j_spring_security_check" method="POST" class="form-container">

        <c:if test="${not empty error}">
            <span class="error-message">${error}</span>
        </c:if>

        <table>
            <tr>
                <td>Username:</td>
                <td>
                    <input class="form-input" size="30" name="user_login" type="text"/>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <input class="form-input" size="30" name="password_login" type="passwords"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="remember-me-text">remember me</span><input name="_spring_security_remember_me" type="checkbox" class="">
                </td>
                <td>
                    <input class="form-btn" type="submit" name="submit" value="submit"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
