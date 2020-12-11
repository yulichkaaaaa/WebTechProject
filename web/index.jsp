<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <style><%@include file="/css/login.css"%></style>
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.reg" var="reg"/>
<body class="align">

  <div class="grid">

    <form action="" method="post" class="form login">

      <header class="login__header">
        <h3 class="login__title">${reg}</h3>
      </header>

      <div class="login__body">
	  <div class="form__field">
          <input type="text" name = "name" placeholder="Name" required>
        </div>

        <div class="form__field">
          <input type="email" name = "email" placeholder="Email" required>
        </div>

        <div class="form__field">
          <input type="password" name = "password" placeholder="Password" required>
        </div>

      </div>

      <footer class="login__footer">
        <input type="submit" value="register">
		<p><a href="login">${login}</a></p>
      </footer>

    </form>
  </div>
</body>
</html>