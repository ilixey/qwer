
<!-- ----------------------------------- FILE SETTINGS ----------------------------------- -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Activity List</title>
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
                <li><a href="/user-list" class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>

<!-- --------------------------------------- CONTENT --------------------------------------- -->

    <div class="row">
        <div class="container">
            <h3 class="text-center">List of Activities for user : <%= request.getParameter("name") %></h3>
            <hr>
            <div class="container text-left">

                <!-- ----------------------------- ACTION BUTTONS ----------------------------- -->

                <a href="/users-list" class="btn btn-info">Back to Users</a>
                <a href="/activities/new_activity" class="btn btn-info">Add New Activity</a>
            </div>
            <br>

            <!-- ------------------------------ RETRIEVED DATA ------------------------------ -->

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Activity</th>
                    <th>Duration</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="activity" items="${listActivity}">
                    <tr>
                        <td><c:out value="${activity.id}" /></td>
                        <td><c:out value="${activity.activity}" /></td>
                        <td><c:out value="${activity.duration}" /></td>
                        <td><c:out value="${activity.publication_date}" /></td>
                        <td>
                            <!-- --------------------- ACTION BUTTONS --------------------- -->

                            <a href="activities/edit-activity?id=<c:out value='${activity.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="activities/delete-activity?id=<c:out value='${activity.id}' />">Delete</a> &nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
