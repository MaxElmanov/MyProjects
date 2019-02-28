<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <h2>Main</h2>
    <label><strong>User name: ${user.name}</strong></label><br/>
    <label><strong>User password: ${user.password}</strong></label>
</body>
</html>
