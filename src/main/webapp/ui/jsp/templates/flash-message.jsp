<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
    <c:if test="${not empty flash_error_message}">
        <div class="alert alert-danger" style="text-align: center">${flash_error_message}</div>
    </c:if>
    <c:if test="${not empty flash_success_message}">
        <div class="alert alert-success" style="text-align: center">${flash_success_message}</div>
    </c:if>
</div>