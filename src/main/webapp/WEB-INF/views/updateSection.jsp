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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Main CSS-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"
	media="all">
</head>

<body>
	<div class="page-wrapper bg-dark p-t-100 p-b-50">
		<div class="wrapper wrapper--w900">
			<div class="card card-6">
				<div class="card-heading">
					<h2 class="title">Add Section</h2>
				</div>
				<div class="card-body">
					<form:form action="../update-section"
						modelAttribute="sectionBean" method="POST">
					<div class="form-row">
						<div class="name">Title</div>
						<div class="value">
							<form:input class="input--style-6" type="text" path="title" />
							<form:hidden path="id" value="${id}"/>
							<form:hidden path="cheatSheetId" value="${cheatSheetId}"/>
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
						<c:if test="${setsec==true}">
							<button type="button" class="btn btn-secondary"
								onclick="location.href = '<c:url value="/"/>'">Finish</button>
						</c:if>

						<button class="btn btn--radius-2 btn--blue-2" type="submit">Send
							Application</button>
					</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade" id="sectionModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<span class="modal-title" id="exampleModalLabel">Message</span>
				</div>
				<div class="modal-body">

					<div class="error-message">
						<strong>${message}</strong>
					</div>

					<button type="button" class="btn btn-secondary"
						onclick="location.href = '<c:url value="/"/>'">Finish</button>
					<button type="button" class="btn btn-primary"
						onclick="location.href = '<c:url value="../section/show-section-form"/>'">Add
						next section</button>
				</div>

			</div>
		</div>
	</div>

	<c:if test="${session==true}">
		<script>
			$(document).ready(function() {
				$('#sectionModal').modal('show');
			});
		</script>
	</c:if>
	<!-- Jquery JS-->
	<!-- Main JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script src="<c:url value="/resources/js/global.js" />"></script>

</body>

</html>
<!-- end document-->