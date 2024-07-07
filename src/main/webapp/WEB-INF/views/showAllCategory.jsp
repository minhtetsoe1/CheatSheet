<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
    href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css" />
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>
    <div aria-live="polite" aria-atomic="true" class="position-relative">
        <div class="toast-container top-0 end-0 p-3">

            <!-- Then put toasts within -->
            <div class="toast" role="alert" aria-live="assertive"
                aria-atomic="true">
                <div class="toast-header">
                    <strong class="me-auto">Message</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="toast"
                        aria-label="Close"></button>
                </div>
                <div class="toast-body">
                    <h4>${message}</h4>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-striped" id="studentTable">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Course ID</th>
                <th scope="col">Type</th>
                <th scope="col">Update/Delete</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${categorylst}" var="cat" varStatus="index">
                <tr>
                    <td scope="row">${index.count}</td>
                    <td>${cat.id}</td>
                    <td>${cat.type}</td>
                    <td>
                        <a href="../category/select/${cat.id}"><button
                                type="button" class="btn btn-success">Update</button></a>
                        <c:if test="${cat.status != 1}">
                            <a href='<c:url value="/category/active/${cat.id}"/>'><button type="button"
                                    class="btn btn-secondary">Active</button></a>
                        </c:if>
                        <c:if test="${cat.status != 0}">
                            <a href='<c:url value="/category/delete/${cat.id}"/>'><button type="button"
                                    class="btn btn-primary">Delete</button></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="<c:url value ="../admin-view" />"><button type="button" class="btn btn-primary">Back</button></a>
    <c:if test="${plan == true}">
        <script>
        document.addEventListener('DOMContentLoaded', function() {
            const toastElList = document.querySelectorAll('.toast');
            const option = {}; // Define the options as needed
            const toastList = [...toastElList].map(toastEl => new bootstrap.Toast(toastEl, option));
            toastList.forEach(toast => toast.show()); // Show toasts manually if needed
        });
        </script>
    </c:if>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRyY8TBjEjD8kcdm2yEfd1by9hD3siKdV3w5MDz02" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/dataTables.bootstrap5.min.js"></script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <script>
        $(document).ready(function() {
            $('#studentTable').DataTable({
                "lengthMenu": [3, 5, 10],
                "pageLength": 5,
            });
        });
    </script>
</body>
</html>
