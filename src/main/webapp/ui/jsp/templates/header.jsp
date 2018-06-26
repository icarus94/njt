<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container-fluid" id="header">
    <nav class="navbar navbar-default">
        <div class="container-fluid">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Hi, ${user.name} ${user.surname} <h1></h1></a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <form:form action="/logout" method="post" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default">Log out</button>
                </form:form>
            </div>

        </div>
    </nav>
</div>