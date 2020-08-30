<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>History</title>
</head>
<body>
<ul>
    <c:forEach var="attribute" items="${requestScope.attribute}">
        <li> <c:out value="${attribute}"/>
        </li>
    </c:forEach>
</ul>

</body>
</html>
