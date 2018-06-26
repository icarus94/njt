<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ToDo Task Scheduler</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" sizes="32x32" href="/favicon.png">
    <link rel='stylesheet' href='/mysite.css'>
    <link rel='stylesheet' href='/webjars/font-awesome/5.0.8/web-fonts-with-css/css/fontawesome-all.min.css'>
    <link rel='stylesheet' href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" id="login-content">
    <div class="row">
        <div class="col-xs-12 col-md-6 col-md-offset-3 text-center">
            <h3>ToDo Task Scheduler Login</h3>
        </div>
    </div>

    <jsp:include page="templates/flash-message.jsp"/>

    <div class="row">
        <div class="jumbotron">
            <div class="container">
                <ul class="nav nav-tabs">
                    <c:choose>
                        <c:when test="${request_type=='login'}">
                            <li class="active"><a href="#login" data-toggle="tab">Login</a></li>
                            <li><a href="#create" data-toggle="tab">Create Account</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="#login" data-toggle="tab">Login</a></li>
                            <li class="active"><a href="#create" data-toggle="tab">Create Account</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <%--mytabcontent--%>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane ${request_type=='login' ? "active" : "fade"}" id="login">
                        <div class="col-xs-12 col-md-6 col-md-offset-3">
                            <form:form action="/loginAction" method="post">
                                <div class="form-group">
                                    <label>Email:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fas fa-envelope"></i></span>
                                        <input type="text" class="form-control" name="username"
                                               placeholder="example: email@gmail.com">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Password:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fas fa-key"></i></span>
                                        <input type="password" class="form-control" name="password"
                                               placeholder="***********">
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-success">Login</button>
                            </form:form>
                        </div>
                        <div class="col-xs-12 col-md-6 col-md-offset-3">
                            <c:if test="${param.error ne null}">
                                <div style="color: red">Email and/or password not valid.</div>
                            </c:if>
                        </div>
                    </div>

                    <div class="tab-pane ${request_type != 'login' ? "active" : "fade"}" id="create">
                        <div class="col-xs-12 col-md-6 col-md-offset-3">
                            <form:form action="${pageContext.request.contextPath}/registerAction" method="post"
                                       modelAttribute="registrationForm">
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fas fa-envelope"></i></span>
                                            <%--<input type="text" class="form-control" id="email" name="email"--%>
                                            <%--placeholder="Your email" required>--%>
                                        <form:input type="email" class="form-control" path="email"
                                                    placeholder="Your email"/>
                                    </div>
                                    <form:errors path="email" class="error_input_message"/>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fas fa-user"></i></span>
                                            <%--<input type="text" class="form-control" id="name" name="username"--%>
                                            <%--placeholder="First name" required>--%>
                                        <form:input class="form-control" path="name" placeholder="First name"/>
                                    </div>
                                    <form:errors path="name" class="error_input_message"/>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fas fa-user"></i></span>
                                        <form:input type="text" class="form-control" path="surname"
                                                    placeholder="Second name"/>
                                    </div>
                                    <form:errors path="surname" class="error_input_message"/>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fas fa-key"></i></span>
                                            <%--<input type="password" class="form-control" name="password"--%>
                                            <%--placeholder="Your account password" required>--%>
                                        <form:input type="password" class="form-control" path="password"
                                                    placeholder="***********"/>
                                    </div>
                                    <form:errors path="password" class="error_input_message"/>
                                </div>

                                <div>
                                    <button class="btn btn-primary" type="submit">Register</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <%--mytabcontent--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>