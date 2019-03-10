<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link href="<%=request.getContextPath()%>/resources/style.css" rel="stylesheet"/>
</head>
<body>
<h1>
	Привет world!
</h1>

<p>  The time on the server is ${serverTime}. </p>
</body>
</html>
