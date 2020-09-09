
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    Hello
    <c:if test="${sessionScope.user == null}">
         User :)
    </c:if>
    <c:if test="${sessionScope.user != null}">
        ${sessionScope.user}
    </c:if>

    <br>
    <c:if test="${sessionScope.user != null}">
        <a href="/logout">Logout</a>
        <a href="/calculation">Calculation</a>
        <a href="/hist">History</a>
        <a href="/account">Account</a>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <a href="/auth">Authorization</a>
        <a href="/reg">Registration</a>
        <a href="/hist">History</a>
    </c:if>

</body>
</html>
