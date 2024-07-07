<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
.head{
display: flex;
}
</style>
</head>
<body>
<div class="head">
<h2>${cheatbean.title }</h2><c:if test="${sameUser==true }">
<a href="../update/${cheatbean.id}"><span>Update</span></a></c:if></div>
<div>${cheatbean.content }</div>
<div>${cheatbean.user.name }</div>
<div>${ cheatbean.createdAt}     ${cheatbean.updatedAt }</div>

<c:forEach items="${sectionlst}" var="s">

  <div class="col-sm-6 ">
    <div class="card">
      <div class="card-body">
     <p id="adminMessage" hidden ></p>
     <div class="head">
        <h5 class="card-title">${ s.title}</h5><c:if test="${sameUser==true}"><a href="<c:url value ="/section/select/${s.id}" />"><span>Update</span></a></c:if></div>
        <p class="card-text">${ s.content}</p>
      </div>
    </div>
  </div>

</c:forEach>

<form:form action="../add-comment" modelAttribute="commentBean" method="post">
<form:textarea path="content" type="text"/>
<form:hidden path="cheatSheet.id" value="${cheatbean.id}"/>
<form:hidden path="user.id" value="${sessionUId}"/>
</form:form>
<script>
$(document).ready(function(){
  $("h5").click(function(){
    $("p").toggle();
  });
});
</script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>