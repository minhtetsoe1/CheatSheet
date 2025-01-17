<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
<!-- meta data -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="stylesheet" href="<c:url value ='resources/css/style2.css'/>">
</head>

<body>
	<!-- top-area Start -->
	<section class="top-area">
		<div class="header-area">
			<!-- Start Navigation -->
			<nav
				class="navbar navbar-default bootsnav  navbar-sticky navbar-scrollspy"
				data-minus-value-desktop="70" data-minus-value-mobile="55"
				data-speed="1000">

				<div class="container">

					<!-- Start Header Navigation -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#navbar-menu">
							<i class="fa fa-bars"></i>
						</button>
						<a class="navbar-brand" href="index.html">list<span>race</span></a>

					</div>
					<!--/.navbar-header-->
					<!-- End Header Navigation -->

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse menu-ui-design"
						id="navbar-menu">
						<ul class="nav navbar-nav navbar-right" data-in="fadeInDown"
							data-out="fadeOutUp">
							<li class="active"><a href="<c:url value ="/" />">Home</a></li>
							<li ><a href="<c:url value ="cheat-sheet/show-cheat-sheet" />">Cheat Sheets</a></li>
							<li ><a href="<c:url value ="cheat-sheet/cheatsheet-create" />">Create</a></li>
							<li ><a href="#">Community</a></li>
							<li ><a href="#">Help</a></li>
							<li class="scroll"><a href="<c:url value="#loginModal" />"
								data-toggle="modal" data-target="#loginModal">Login</a></li>
							<li class="scroll"><a href="<c:url value="#signupModal" />"
								data-toggle="modal" data-target="#signupModal">Register</a></li>
								
							<c:if test="${result==true}">
								<li>
									<div style="margin-top: 35px;">
										<a href="<c:url value="/profile" />"><i class="bi bi-person-circle"></i></a>
									</div>
								</li>
							</c:if>
						</ul>
						
						<!--/.nav -->
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!--/.container-->
			</nav>
			<!--/nav-->
			<!-- End Navigation -->
		</div>
		<!--/.header-area-->

		<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">

						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<span class="modal-title" id="exampleModalLabel">Login Form</span>
					</div>
					<div class="modal-body">

						<div class="error-message">
							<strong>${loginResult}</strong>
						</div>
						<form:form action="${pageContext.request.contextPath}/login"
							class="row" id="loginForm" modelAttribute="registerBean"
							method="post">

							<div class="m-5">

								<span class="error-message" id="loginnameError"></span>
								<form:input type="text" path="email" class="form-control"
									id="loginname" name="loginName" placeholder="Email" />
							</div>
							<c:if test="${passworderror1==true}">
								<div class="error-message">
									<strong>${passworderror}</strong>
								</div>
							</c:if>
							<div>
								<span class="error-message" id="loginpasswordError"></span>
								<form:input type="password" path="password" class="form-control"
									id="loginpassword" name="loginPassword" placeholder="Password"/>
							</div>

							<div class="col-6" style="margin: 10px 10px;">
								<a href="user/forgotPassword">forgot password?</a>
							</div>
							<div class="col-12">
								<button type="submit" class="btn btn-primary">LOGIN</button>
							</div>
						</form:form>
					</div>

				</div>
			</div>
		</div>
		<div class="modal fade" id="signupModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">

						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<span class="modal-title" id="exampleModalLabel">Register
							Form</span>
					</div>
					<div class="modal-body">
						<div class="login">
							<span>${success}</span> <span class="error mb-4">${error}</span>

							<form:form action="${pageContext.request.contextPath}/register"
								class="row" id="registrationForm" method="post"
								modelAttribute="registerBean">
								<div>
									<span id="usernameError" class="error"></span>
									<form:input type="text" path="name" class="form-control"
										id="username" name="signupName" placeholder="Name" />
								</div>
								<div>
									<span id="emailError" class="error"></span>
									<span  class="error"> ${emailError }</span>
									<form:input type="email" path="email" class="form-control"
										id="email" name="signupEmail" placeholder="Email" />
								</div>
								<div>
									<span id="passwordError" class="error"></span>
									<form:input type="password" path="password"
										class="form-control" id="password" name="signupPassword"
										placeholder="Password" />
								</div>

								<div>
									<span id="confirmPasswordError" class="error"></span>
									<form:input type="password" path="conPassword"
										class="form-control" id="confirmPassword"
										name="signupConPassword" placeholder="ComfirmPassword" />
								</div>

								<div class="col-12">
									<button type="submit" class="btn btn-primary">SIGN UP</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- Modal -->

		<c:if test="${loginError}">
			<script>
				$(document).ready(function() {
					$('#loginModal').modal('show');
				});
			</script>
		</c:if>
		<c:if test="${registerError}">
			<script>
			$(document).ready(function() {
				$('#signupModal').modal('show');
			});
		</script>
			</c:if>
			<script src="<c:url value="/resources/js/register.js" />"></script>
			<script src="<c:url value="/resources/js/login.js" />"></script>
</body>

</html>