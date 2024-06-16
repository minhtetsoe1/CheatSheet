<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <!-- meta data -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    </head>

	<body>

    		<div class="modal fade" id="signupModal" tabindex="-1" role="dialog"
            		aria-hidden="true">
            		<div class="modal-dialog modal-lg" role="document">
            			<div class="modal-content rounded-0 border-0 p-4">
            				<div class="modal-header border-0">
            					<h3>Register</h3>
            					<button type="button" class="close" data-dismiss="modal"
            						aria-label="Close">
            						<span aria-hidden="true">&times;</span>
            					</button>
            				</div>
            				<div class="modal-body">
            					<div class="login">
            						<span>${success}</span> <span class="error mb-4">${error}</span>

            						<form:form
            							action="${pageContext.request.contextPath}/user/register"
            							class="row" id="registrationForm" method="post"
            							modelAttribute="registerBean">
            							<div class="col-12">
            								<span id="usernameError"  class="error"></span>
            								<form:input type="text" path="name" class="form-control mb-3"
            									id="username" name="signupName" placeholder="Name" />
            							</div>
            							<div class="col-12">
            								<span id="emailError" class="error"></span><br>
            								<form:input type="email" path="email" class="form-control mb-3"
            									id="email" name="signupEmail" placeholder="Email" />
            							</div>
            							<div class="col-12">
            								<span id="passwordError" class="error"></span><br>
            								<form:input type="password" path="password"
            									class="form-control mb-3" id="password" name="signupPassword"
            									placeholder="Password" />
            							</div>

            							<div class="col-12">
            								<span id="confirmPasswordError" class="error"></span><br>
            								<form:input type="password" path="conPassword"
            									class="form-control mb-3" id="confirmPassword"
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
            	<!-- Modal -->



             </body>

                </html>