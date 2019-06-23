<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Turtle</title>
    <link href="/resources/JSPStyle.css" rel="stylesheet"/>
    <link href="https://img.icons8.com/ios/1600/turtle.png" rel="icon"/>
    <script type="text/javascript" src="/resources/function/js/script.js" defer></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>

    <h2>Function</h2>

    <img src="/resources/function/imgs/function.PNG"/>

    <p>it's simple genetic algorithm which gradually pick up the best values based on previous numbers to get the biggest amount for the function.</p>

    <form:form action="/generateGeneration" method="get" modelAttribute="genofond">
        <div class="genes">
            <div class="geneX">
                <label>X:</label>
                <form:input path="x1" maxlength="2"></form:input>
                <form:input path="x2" maxlength="2"></form:input>
                <form:input path="x3" maxlength="2"></form:input>
                <form:input path="x4" maxlength="2"></form:input>
            </div>
            <div class="geneY">
                <label>Y:</label>
                <form:input path="y1" maxlength="2"></form:input>
                <form:input path="y2" maxlength="2"></form:input>
                <form:input path="y3" maxlength="2"></form:input>
                <form:input path="y4" maxlength="2"></form:input>
            </div>
        </div>
        <input id="btn" type="submit" value="Launch"/>
    </form:form>
    <c:if test="${!empty listResults}">
        <%--<c:forEach var="result" items="${listResults}">--%>
            <h2><c:out value="${listResults}"/></h2>
        <%--</c:forEach>--%>
    </c:if>
    <a href="/">GoToIndex</a><br><hr>

    <div id="funTest">
        <label>To get function's answer with your numbers.</label>
        X: <input type="number" id="x" value=""/>
        Y: <input type="number" id="y" value=""/><br>
        <input type="button" id="getFunAnswer" value="push"/>
    </div>
</body>
</html>
