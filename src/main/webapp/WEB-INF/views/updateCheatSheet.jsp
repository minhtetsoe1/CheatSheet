<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<!-- Title Page-->
<title>Apply for job by Colorlib</title>

<!-- Font special for pages-->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"
	rel="stylesheet">
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>

<!-- Main CSS-->
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"
	media="all">
</head>

<body>
	<div class="page-wrapper bg-dark p-t-100 p-b-50">
		<div class="wrapper wrapper--w900">
			<div class="card card-6">
				<div class="card-heading">
					<h2 class="title">Add Cheat Sheet</h2>
				</div>
				<div class="card-body">
					<form:form action="../update-cheat-sheet"
						modelAttribute="cheatSheetBean" method="POST"
						enctype="multipart/form-data">
						<div class="form-row">
							<div class="name">Title</div>
							<div class="value">
								<form:input class="input--style-6" type="text" path="title" />
								<form:hidden path="id" value="${id}"/>
							</div>
						</div>
						<div class="form-row">
							<div class="name">Category</div>
							<div class="value">
								<div class="input-group">
									<c:forEach var="category" items="${categorylst}">
										<form:checkbox path="category" value="${category.id}" />
										<label>${category.type}</label>
									</c:forEach>
								</div>
							</div>
						</div>

						<div class="form-row">
							<div class="name">Content</div>
							<div class="value">
								<div class="input-group">
									<form:textarea class="textarea--style-6" path="content"
										placeholder="Message sent to the employer"></form:textarea>
								</div>
							</div>
						</div>
			
						<div class="card-footer">
							<button class="btn btn--radius-2 btn--blue-2" type="submit">Send
								Application</button>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
	<!-- Jquery JS-->
	<!-- Main JS-->
	<script src="<c:url value="/resources/js/global.js" />"></script>

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->