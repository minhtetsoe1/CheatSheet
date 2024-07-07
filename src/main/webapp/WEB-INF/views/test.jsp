<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    // Get the current date
    Date currentDate = new Date();

    // Create a date formatter
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    // Format the current date
    String formattedDate = dateFormatter.format(currentDate);
%>
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
                    <h2 class="title">Apply for job</h2>
                </div>
                <div class="card-body">
                    <form:form action="test" modelAttribute="testBean" method="POST" enctype="multipart/form-data">
               
                        <div class="form-row">
                            <div class="name">Upload Photo</div>
                            <div class="value">
                                <div class="input-group js-input-file">
                                    <form:input class="input-file" type="file" path="file" id="file"/>
                                    <form:label class="label--file" path="file" for="file">Choose file</form:label>
                                    <span class="input-file__info">No file chosen</span>
                                </div>
                                <div class="label--desc">Upload your CV/Resume or any other relevant file. Max file size 50 MB</div>
                            </div>
                        </div>
                        <div class="card-footer">
                    <button class="btn btn--radius-2 btn--blue-2" type="submit">Send Application</button>
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