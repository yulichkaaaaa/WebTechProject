<%--
  Created by IntelliJ IDEA.
  User: Юля
  Date: 29.11.2020
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <style><%@include file="/css/login.css"%></style>
</head>
<body class="align">

<div class="grid">

    <form method="post" class="form login">

        <header class="login__header">
            <h3 class="login__title">Login</h3>
        </header>

        <div class="login__body">

            <div class="form__field">
                <input type="email" name = "email" placeholder="Email" required>
            </div>

            <div class="form__field">
                <input type="password" name = "password" placeholder="Password" required>
            </div>

        </div>

        <footer class="login__footer">
            <input type="submit" value="Login">
            <p><a href="register">Register</a></p>
        </footer>

    </form>

    <p>${error}</p>
</div>
</body>
</html>