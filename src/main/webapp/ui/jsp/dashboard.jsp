<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="templates/header-data.jsp"/>
<body>
<div>
    <jsp:include page="templates/header.jsp"/>
    <div class="container">
        <jsp:include page="templates/flash-message.jsp"/>

        <jsp:include page="templates/add-task-category.jsp"/>

        <jsp:include page="templates/dashboard-content.jsp"/>
    </div>
</div>
</body>
</html>