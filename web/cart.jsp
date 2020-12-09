<%@ page import="com.yuliana.beans.Product" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
							<li><a href="register">Sign up</a></li>
							<li><a href="login">Login</a></li>
							<li><a href="cart">Cart</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
        <div class="table-responsive">
            <table class="table table-hover table-bordered">
                <thead>
                    <tr>
                        <th>Артикул</th>
                        <th>Название</th>
                        <th>Цена</th>
                        <th>Количество</th>
                        <th>Сумма</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="cart">
                <%HashSet<Product> products =(HashSet<Product>) request.getAttribute("cartItems");
                    for (Product p : products){

                        {%>
                    <tr>
                        <td><%=p.getProductId()%></td>
                        <td><%=p.getTitle()%></td>
                        <td><%=p.getPrice()%></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%}
                    }
                %>
                </tbody>
            </table>
        </div>
        <div>Итого: <span id="total-cart-summa">65000</span> руб.</div>
        <br/>
        <a class="btn btn-info" href="order.html">Оформить заказ</a>
    </body>
</html>