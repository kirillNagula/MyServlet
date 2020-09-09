<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
    <form action="/account" method="post">
        <input name="name" type="text" placeholder="name" minlength="5" required>
        <input name="password" type="text" placeholder="password" minlength="5" required>
        <button>Update</button>
    </form>
    <form action="/delete" method="post" >
        <button>Delete</button>
    </form>

</body>
</html>
