<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.08.2020
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Calculation</title>
</head>
<body>
    <a href="/">Main</a>
    <form action="/calculation" method="post">
        значение1 <input type="text" name="num1">
        значение2 <input type="text" name="num2">
        операция <select name="type">
        <option value="sum">Суммировать</option>
        <option value="sub">Отнять</option>
        <option value="mult">Умножить</option>
        <option value="div">Разделить</option>
    </select>
        <br>
        <button>Подсчитать</button>
    </form>
    <c:if test="${requestScope.result != null}">
        <h1>
            ${requestScope.result}
        </h1>
    </c:if>

</body>
</html>
