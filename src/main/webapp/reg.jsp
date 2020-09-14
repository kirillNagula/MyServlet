<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.08.2020
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/reg" method="post">
    login=<input type="text" name="login" maxlength="20" minlength="2" required/>
    password=<input type="text" name="password" maxlength="20" minlength="2" required/>
    name=<input type="text" name="name"/>
    <button>Отправить</button>
</form>
${requestScope.notUnique}
</body>
</html>
