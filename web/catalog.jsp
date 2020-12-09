<%@ page import="java.util.HashSet" %>
<%@ page import="com.yuliana.beans.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Catalog</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<style><%@include file="/css/style.css"%></style>
		<style><%@include file="/css/font-awesome.min.css"%></style>
		<style><%@include file="/css/bootstrap.css"%></style>
		<!-- Favicon -->
		<link rel="shortcut icon" href="#">
	</head>
	<body>
		<div class="wrapper">
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

			<div class="team" id="team">
				<div class="container">
					<div class="default-heading">
						<h2>Catalog</h2>
					</div>
					<div class="filters">
						<form method="post">
							<select name="categories">
								<option>choose a category</option>
								<option>dress</option>
								<option>sweater</option>
								<option>jeans</option>
								<option>coat</option>
								<option>boots</option>
								<option>skirt</option>
							</select>
							<input type="checkbox" name="stock" value="in"> in stock
							<div class="filters_button">
								<input type="submit" value="confirm">
							</div>
						</form>
					</div>

					<%HashSet<Product> products =(HashSet<Product>) request.getAttribute("products");
					for (Product p : products){

						{%>
							<div class="col-md-3 col-sm-3">
								<div class="member">
									<img class="img-responsive" src="${pageContext.request.contextPath}<%=p.getPictureName()%>" alt="Team Member" />
									<h3><%=p.getTitle()%></h3>
									<span class="dig"><%=p.getPrice()%></span>
									<a href="cart/id<%=p.getProductId()%>">добавить в корзину</a>
								</div>
							</div>
						<%}
					}
					%>
			     </div>
			</div>
		</div>
	</body>	
</html>