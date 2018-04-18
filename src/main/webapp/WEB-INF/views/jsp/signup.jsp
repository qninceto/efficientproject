<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="error.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>Efficient Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/custom.js"></script>

</head>
<body  background="/img/Background1.jpg">
	<div class="text-info">
		<div class="text-left">
			<h1>Efficient Project</h1>
		</div>
	</div>
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">Sign in</div>
			</div>
			<div class="panel-body">
				<form:form id="login-form" modelAttribute="user" action="signup" method="post" class="form-horizontal">

					<div id="signupalert" style="display: none"
						class="alert alert-danger">
						<p>Error:</p>
						<span></span>
					</div>

					<div class="form-group">
						<form:label path="firstName" class="col-md-3 control-label">First Name</form:label>
						<div class="col-md-9">
							<form:input type="text" path="firstName" name="first-name" 
								class="form-control" placeholder="First name" required="required"/>
						</div>
					</div>

					<div class="form-group">
						<form:label  path="lastName" class="col-md-3 control-label">Last Name</form:label >
						<div class="col-md-9">
							<form:input type="text" path="lastName" name="last-name" 
								class="form-control" placeholder="Last name" required="required"/>
						</div>
					</div>


					<div class="form-group">
						<form:label  path="email" class="col-md-3 control-label">Email</form:label >
						<div class="col-md-9">
							<form:input type="email" path="email" name="email" 
								class="form-control" placeholder="Email Address" required="required"/>
						</div>
					</div>


					<div class="form-group">
					<form:label  path="password" class="col-md-3 control-label">Password</form:label >
						<div class="col-md-9">
							<form:input id="pass1" type="password" path="password" name="password" 
								class="form-control" placeholder="Password" required="required"/>
						</div>
					</div>

					<div class="form-group">
						<label for="repPassword" class="col-md-3 control-label">
							Repeat Password</label>
						<div class="col-md-9">
							<input id="pass2" type="password" name="repPassword" 
								class="form-control" placeholder="Repeat Password" required="required"/>
						</div>
					</div>

					<div class="checkbox">
						<label><form:checkbox id="isAdmin" path="admin" 
							name="isAdmin" value ="false" onclick="myFunction2()"/>
							Register organization</label>
					</div>
	
					<form:form  modelAttribute="organization" method="post">
					<div class="form-group" style="display: none;" id="organization">
						<form:label  path="name" class="col-md-3 control-label">Organization name</form:label >
						<div class="col-md-9">
							<form:input id="organization" path="name"  type="text" name="organization" class="form-control" placeholder="Name"/>
						</div>
					</div>
					</form:form>

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
							<button onclick="myFunction()" id="btn-signup" type="submit"
								class="btn btn-info">Sign up</button>

							<span style="margin-left: 8px;">Already a member?<a
								href="./login"> Log in here </a></span>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


</body>
</html>