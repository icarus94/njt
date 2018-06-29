<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="admin/header-data.jsp"/>
<body>
<div>
    <jsp:include page="templates/header.jsp"/>
    <div class="container">
        <jsp:include page="templates/flash-message.jsp"/>

        <jsp:include page="admin/user-dashboard.jsp"/>
    </div>
</div>
</body>
</html>