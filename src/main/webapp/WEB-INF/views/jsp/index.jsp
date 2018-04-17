<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="error.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html">

<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>Efficient Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>


</head>
<body  background="img/Background1.jpg">
	<div class="text-info">
		<div class="text-left">
			<h1>Efficient Project</h1>
		</div>
	</div>
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">Login in </div>
			</div>
			<div class="panel-body">
				<form:form id="login-form" modelAttribute="user" action="login" method="post" class="form-horizontal">
					<div id="signupalert" style="display: none"
						class="alert alert-danger">
						<p>Error:</p>
						<span></span>
					</div>
					<div class="form-group">
						<label for="email" class="col-md-3 control-label">Email</label>
						<div class="col-md-9">
							<form:input type="email" path="email" name="email" class="form-control" placeholder="Email Address" required="required"/>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-md-3 control-label">Password</label>
						<div class="col-md-9">
							<form:input id="pass1" type="password" path="password" name="password" class="form-control" placeholder="Password" required="required"/>
						</div>
					</div>

					<c:if test="${not empty errorMessage }">
						<div class="form-group">
							<div class="col-md-offset-3 col-md-9">
								<span style="color: red"> <c:out
										value="${ errorMessage }" />
								</span>
							</div>
						</div>
					</c:if>

					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info">
								<i class="icon-hand-right"></i> Login
							</button>

							<span style="margin-left: 8px;">Don`t have a registration?<a
								href="./signup"> Sign up here </a></span>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


</body>
</html>