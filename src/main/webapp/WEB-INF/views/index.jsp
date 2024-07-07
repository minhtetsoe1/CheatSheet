<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<!-- meta data -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!--font-family-->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- title of site -->
<title>Directory Landing Page</title>

<!-- For favicon png -->
<link rel="shortcut icon" type="image/icon"
	href="<c:url value ='resources/logo/favicon.png'/>" />

<!--font-awesome.min.css-->
<link rel="stylesheet"
	href="<c:url value ='resources/css/font-awesome.min.css'/>">

<!--linear icon css-->
<link rel="stylesheet"
	href="<c:url value ='resources/css/linearicons.css'/>">

<!--animate.css-->
<link rel="stylesheet"
	href="<c:url value ='resources/css/animate.css'/>">

<!--flaticon.css-->
<link rel="stylesheet"
	href="<c:url value ='resources/css/flaticon.css'/>">

<!--slick.css-->
<link rel="stylesheet" href="<c:url value ='resources/css/slick.css'/>">
<link rel="stylesheet"
	href="<c:url value ='resources/css/slick-theme.css'/>">

<!--bootstrap.min.css-->
<link rel="stylesheet"
	href="<c:url value ='resources/css/bootstrap.min.css'/>">

<!-- bootsnav -->
<link rel="stylesheet"
	href="<c:url value ='resources/css/bootsnav.css'/>">

<!--style.css-->
<link rel="stylesheet" href="<c:url value ='resources/css/style.css'/>">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!--responsive.css-->
<link rel="stylesheet"
	href="<c:url value ='resources/css/responsive.css'/>">
<style>
.list-topics-content {
	margin-top: 90px;
}
</style>
</head>

<body>

	<jsp:include page="indexHeader.jsp"></jsp:include>

	<!--list-topics start -->
	<section id="list-topics" class="list-topics top">
		<div class="container">
			<div class="list-topics-content">
				<ul>
				<c:forEach items="${categorylst}" var="cat">
					<li><a href="<c:url value ="/cheat-sheet/selectById/${cat.id}" />">
						<div class="single-list-topics-content">
							<div class="single-list-topics-icon">
								
							</div>
							<h2>
								${cat.type }
							</h2>
							
						</div></a>
					</li>
					</c:forEach>
					
				</ul>
			</div>
		</div>
		<!--/.container-->

	</section>


	
	<section id="blog" class="blog">
		<div class="container">
			<div class="section-header">
				<h2> Latest Cheat Sheet</h2>
				
			</div>
			<!--/.section-header-->
			<div class="blog-content">
				<div class="row">
				<c:forEach items="${LatestCheatSheet}" var="cheat">
					<div class="col-md-4 col-sm-6">
						<div class="single-blog-item">
							<div class="single-blog-item-img">
								<a href="cheat-sheet/show/${cheat.id }"><img src="${cheat.image }" alt="blog image"></a>
							</div>
							<div class="single-blog-item-txt">
								<h2>
									<a href="cheat-sheet/show/${cheat.id }">${cheat.title }</a>
								</h2>
								<h4>
									posted <span>by</span> <a href="#">${cheat.user.name}</a>${cheat.createdAt }<c:if
										test="${cheat.updatedAt!=null}">
										<small class="text-body-secondary"><span>${cheat.updatedAt }</span></small>
									</c:if>
								</h4>
								<c:forEach var="category" items="${cheat.category}">
										
										<label>${category.type}</label>
									</c:forEach>
								<p>${cheat.content}</p>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<!--/.container-->
	</section>

	<!--/.footer-->
	<!--footer end-->
	<script src="<c:url value="/resources/js/cheatsheet.js" />"></script>
	<!-- Include all js compiled plugins (below), or include individual files as needed -->

	<script src="<c:url value="/resources/js/jquery.js" />"></script>

	<!--modernizr.min.js-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>

	<!--bootstrap.min.js-->
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

	<!-- bootsnav js -->
	<script src="<c:url value="/resources/js/bootsnav.js" />"></script>

	<!--feather.min.js-->
	<script src="<c:url value="/resources/js/feather.min.js" />"></script>
	<!-- counter js -->
	<script src="<c:url value="/resources/js/jquery.counterup.min.js" />"></script>
	<script src="<c:url value="/resources/js/waypoints.min.js" />"></script>

	<!--slick.min.js-->
	<script src="<c:url value="/resources/js/slick.min.js" />"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<!--Custom JS-->
	<script src="<c:url value="/resources/js/custom.js" />"></script>
</body>

</html>