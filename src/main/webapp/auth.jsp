<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.08.2020
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<form action="/auth" method="post">
    login=<input type="text" name="login"/>
    password=<input type="text" name="password"/>
    <button>Отправить</button>
</form>

</body>
</html>
