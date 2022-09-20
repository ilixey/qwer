
<!-- ----------------------------------- FILE SETTINGS ----------------------------------- -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>User List</title>
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

    <div class="row">
        <div class="container">
            <h3 class="text-center">List of Users</h3>
            <hr>
            <div class="container text-left">

                <!-- ----------------------------- ACTION BUTTONS ----------------------------- -->

                <a href="<%= request.getContextPath()%>/new-user" class="btn btn-info">Add New User</a>
            </div>
            <br>

            <!-- ------------------------------ RETRIEVED DATA ------------------------------ -->

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Age</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <!--
                    listUser
                    Создан в команде commands.impl.user.ShowAllUsers
                    Передан в user-list.jsp методом request.setAttribute("listUser", userList);
                    Является List<User>
                    Заполняется моделями User в services.dbUserService.getUsers()
                -->
                <c:forEach var="user" items="${listUser}">
                    <tr>
                        <td><c:out value="${user.id}" /></td>
                        <td><c:out value="${user.name}" /></td>
                        <td><c:out value="${user.surname}" /></td>
                        <td><c:out value="${user.age}" /></td>
                        <td>
                            <!-- --------------------- ACTION BUTTONS --------------------- -->
                            <!--
                                Синтаксис ссылки
                                ___command___?___url_variable1____&___url_variable2.....
                                н.п.
                                http://localhost:8081/edit-user?id=1
                            -->
                            <a href="<%= request.getContextPath()%>/edit-user?id=<c:out value='${user.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="<%= request.getContextPath()%>/delete-user?id=<c:out value='${user.id}' />">Delete</a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="<%= request.getContextPath()%>/activity-list?userId=<c:out value='${user.id}' />&name=<c:out value='${user.name}'/>">Show Activities</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
