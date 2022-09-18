
<!-- ----------------------------------- FILE SETTINGS ----------------------------------- -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>User Form</title>
    <meta charset=utf-8>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<!-- --------------------------------------- HEADER --------------------------------------- -->

    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
            <ul class="navbar-nav">
                <li><a href="<%= request.getContextPath()%>/users-list" class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>

<!-- --------------------------------------- CONTENT --------------------------------------- -->

    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">

                <!-- ---------------------- FORM GOAL LOGIC ---------------------- -->

                <c:if test="${user != null}">
                    <form action="<%= request.getContextPath()%>/update-user" method="post">
                </c:if>
                <c:if test="${user == null}">
                    <form action="<%= request.getContextPath()%>/insert-user" method="post">
                </c:if>

                    <!-- ------------------- FORM NAME LOGIC ------------------- -->

                    <caption>
                        <h2>
                            <c:if test="${user != null}">Edit User</c:if>
                            <c:if test="${user == null}">Add New User</c:if>
                        </h2>
                    </caption>

                        <!-- -------- HIDE "USER ID" IF IT IS USER EDIT ACTION -------- -->

                        <c:if test="${user != null}">
                            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                        </c:if>

                        <!-- ------------------- USER NAME ------------------- -->
                        <fieldset class="form-group">
                            <label>User Name</label>
                            <input type="text"
                                   value="<c:out value='${user.name}' />"
                                   class="form-control"
                                   name="name"
                                   required="required" />
                        </fieldset>

                        <!-- ------------------ USER SURNAME ------------------- -->
                        <fieldset class="form-group">
                            <label>User surname</label>
                            <input type="text"
                                   value="<c:out value='${user.surname}' />"
                                   class="form-control"
                                   name="surname"
                                   required="required"/>
                        </fieldset>

                        <!-- ------------------- USER AGE ------------------- -->
                        <fieldset class="form-group">
                            <label>User age</label>
                            <input type="number"
                                   min = "0"
                                   value="<c:out value='${user.age}' />"
                                   class="form-control"
                                   name="age"
                                   required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
            </div>
        </div>
    </div>
</body>
</html>