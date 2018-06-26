<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="templates/header-data.jsp"/>
<body>
<div>
    <jsp:include page="templates/header.jsp"/>
    <div class="container">
        <jsp:include page="templates/add-task-category.jsp"/>

        <jsp:include page="templates/dashboard-content.jsp"/>

        <c:forEach var="user" items="${users}">
            <div class="row" style="border: 1px solid green; padding: 10px">
                <div class="col-md-4 text-center">${user.name}</div>
                <div class="col-md-4 text-center">${user.email}</div>
                <div class="col-md-4 text-center">${user.address}</div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>