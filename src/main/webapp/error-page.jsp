
<!-- ----------------------------------- FILE SETTINGS ----------------------------------- -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>ERRRROR</title>
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
                <li>
                    <a href="<%= request.getContextPath()%>/users-list" class="nav-link">Users</a>
                </li>
            </ul>
        </nav>
    </header>
    <br>

<!-- --------------------------------------- CONTENT --------------------------------------- -->

    <div class="row">
        <div class="container">
            <br />
            <h1 class="text-center">Error</h1>
            <h2 class="text-center">Something went wrong :C</h2>
        </div>
    </div>
</body>
</html>
