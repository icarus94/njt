<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="admin/header-data.jsp"/>
<body>
<div>
    <jsp:include page="templates/header.jsp"/>
    <div class="container">
        <jsp:include page="templates/flash-message.jsp"/>

        <jsp:include page="admin/main-view.jsp"/>
    </div>
    <jsp:include page="admin/user-create-modals.jsp"/>
    <jsp:include page="admin/user-edit-modal.jsp"/>
    <jsp:include page="admin/user-delete-modal.jsp"/>
</div>
</body>
</html>