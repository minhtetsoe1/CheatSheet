<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>Insert title here</title>
<!-- 
<style>
.card {
	height: 300px;
	margin: 20px;
	--bs-card-border-color: none;
}

.card-con {
	display: flex;
}

.img-card {
	height: 200px;
	with: 300px;
}

.single-explore-item {
	border: none;
	margin: 10px;
	background: #ece4e4;
}

.pagination {
	display: flex;
	justify-content: center;
	list-style: none;
	padding: 0;
}

.page-item {
	margin: 0 5px;
}

.page-item .page-link {
	padding: 10px 15px;
	text-decoration: none;
	color: #007bff;
	border: 1px solid #dee2e6;
	border-radius: 0.25rem;
	background-color: #fff;
}

.page-item .page-link:hover {
	background-color: #e9ecef;
}

.page-item.active .page-link {
	color: #fff;
	background-color: #007bff;
	border-color: #007bff;
}

.page-item.disabled .page-link {
	color: #6c757d;
	pointer-events: none;
	background-color: #fff;
	border-color: #dee2e6;
}

.page-item.dots .page-link {
	pointer-events: none;
	border: none;
}
</style> -->
</head>
<body>

	<section id="explore" class="explore">
		<div class="container card-con">
			<div class="justify-content-center card-content">
				<c:forEach items="${allCheatSheet}" var="cheat">
					<div class="col-md-6 card-content">
						<div class="single-explore-item ">
							<div class="card m-3" style="max-width: 540px;">
								<div class="row g-0">
									<div class="col-md-4 pt-3">
									<c:if test="${url != true }">
										<a href="../show/${cheat.id }"><img src="<c:url value='/${cheat.image }' />"
											class="img-fluid rounded-start img-card" alt="..."></a></c:if>
											<c:if test="${url == true }">
										<a href="../cheat-sheet/show/${cheat.id }"><img src="<c:url value='/${cheat.image }' />"
											class="img-fluid rounded-start img-card" alt="..."></a></c:if>
									</div>
									<div class="col-md-8">
										<div class="card-body">
										<c:if test="${url != true }">
										<a href="../show/${cheat.id }"><h5 class="card-title">${cheat.title }</h5></a>
										</c:if>
											<c:if test="${url == true }">
										<a href="../cheat-sheet/show/${cheat.id }"><h5 class="card-title">${cheat.title }</h5></a>
										</c:if>
											<p class="card-text">${cheat.content}</p>
											<p class="card-text">
												<small class="text-body-secondary">Create At :
													${cheat.createdAt }</small>
												<c:if test="${cheat.updatedAt!=null}">
													<small class="text-body-secondary">Update At : <span>${cheat.updatedAt }</span>
												</c:if>
												</small>
											</p>
											<c:forEach var="category" items="${cheat.category}">
										
										<label>${category.type}</label>
									</c:forEach>

											<p>${cheat.user.name}</p>
											<c:forEach var="categoryBean" items="${cheat.category}">
												<a href=""><span>${categoryBean.type}</span></a>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
		<div class="pagination"></div>
		<!--/.container-->

	</section>
	<script src="<c:url value="/resources/js/cheatsheet.js" />"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>