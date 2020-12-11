<%@ page import="java.util.HashSet" %>
<%@ page import="com.yuliana.beans.CartItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Корзина</title>
        <meta name="description" content="Тестовый интернет-магазин от webdevkin-a. Корзина, редактирование и переход на оформление заказа" />
        <link href='https://fonts.googleapis.com/css?family=Ubuntu:400,700&subset=latin,cyrillic-ext' rel='stylesheet' type='text/css'>
	    <style><%@include file="/css/style.css"%></style>
	    <style><%@include file="/css/font-awesome.min.css"%></style>
	    <style><%@include file="/css/bootstrap.css"%></style>
    </head>
    <fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
    <fmt:setBundle basename="lang" var="loc"/>
    <fmt:message bundle="${loc}" key="lang.label.cart" var="cart"/>
    <fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
    <fmt:message bundle="${loc}" key="lang.label.reg" var="reg"/>
    <fmt:message bundle="${loc}" key="lang.label.title" var="title"/>
    <fmt:message bundle="${loc}" key="lang.label.price" var="price"/>
    <fmt:message bundle="${loc}" key="lang.label.sum" var="sum"/>
    <fmt:message bundle="${loc}" key="lang.label.count" var="count"/>
    <fmt:message bundle="${loc}" key="lang.label.total" var="total"/>
    <fmt:message bundle="${loc}" key="lang.label.pay" var="pay"/>
    <fmt:message bundle="${loc}" key="lang.label.catalog" var="catalog"/>
    <body data-page="cart">
        <header>
			<nav class="navbar navbar-default" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"><img class="img-responsive"/>AetheticsShop</a>
					</div>

					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="register">${reg}</a></li>
							<li><a href="login">${login}</a></li>
                            <li><a href="catalog">${catalog}</a></li>
							<li><a href="cart">${cart}</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
        <div class="table-responsive">
            <table class="table table-hover table-bordered">
                <thead>
                    <tr>
                        <th>${title}</th>
                        <th>${price}</th>
                        <th>${count}</th>
                        <th>${count}</th></tr>
                </thead>
                <tbody id="cart">
                <%HashSet<CartItem> cartItems =(HashSet<CartItem>) request.getAttribute("cartItems");
                    for (CartItem i : cartItems){

                        {%>
                    <tr>
                        <td><%=i.getTitle()%></td>
                        <td><%=i.getPrice()%></td>
                        <td><%=i.getCount()%></td>
                        <td><%=i.getPrice() * i.getCount()%></td>
                    </tr>
                    <%}
                    }
                %>
                </tbody>
            </table>
        </div>
        <div>${total}: <fmt:formatNumber type = "number" maxFractionDigits="2" value = "${amount}" />$.</div>
        <br/>
        <form method="post">
            <input type="submit" value="pay">
        </form>
    </body>
</html>