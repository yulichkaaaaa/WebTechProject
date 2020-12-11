<%@ page import="java.util.HashSet" %>
<%@ page import="com.yuliana.beans.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename="lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.label.cart" var="cart"/>
	<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
	<fmt:message bundle="${loc}" key="lang.label.reg" var="reg"/>
	<fmt:message bundle="${loc}" key="lang.label.catalog" var="catalog"/>
	<fmt:message bundle="${loc}" key="lang.label.choose" var="choose"/>
	<fmt:message bundle="${loc}" key="lang.label.dress" var="dress"/>
	<fmt:message bundle="${loc}" key="lang.label.sweater" var="sweater"/>
	<fmt:message bundle="${loc}" key="lang.label.skirt" var="skirt"/>
	<fmt:message bundle="${loc}" key="lang.label.jeans" var="jeans"/>
	<fmt:message bundle="${loc}" key="lang.label.coat" var="coat"/>
	<fmt:message bundle="${loc}" key="lang.label.boots" var="boots"/>
	<fmt:message bundle="${loc}" key="lang.label.in_stock" var="in_stock"/>
	<fmt:message bundle="${loc}" key="lang.label.add_to_cart" var="add_to_cart"/>
	<fmt:message bundle="${loc}" key="lang.label.catalog" var="catalog"/>

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
								<li><a href="register">${reg}</a></li>
								<li><a href="login">${login}</a></li>
								<li><a href="catalog">${catalog}</a></li>
								<li><a href="cart">${cart}</a></li>
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
								<option value="choose a category">${choose}</option>
								<option value="dress">${dress}</option>
								<option value="sweater">${sweater}</option>
								<option value="jeans">${jeans}</option>
								<option value="coat">${coat}</option>
								<option value="boots">${boots}</option>
								<option value="skirt">${skirt}</option>
							</select>
							<input type="checkbox" name="stock" value="in"> ${in_stock}
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
									<a href="cart/id<%=p.getProductId()%>">${add_to_cart}</a>
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