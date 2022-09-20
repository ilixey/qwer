
<!-- ----------------------------------- FILE SETTINGS ----------------------------------- -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Activity form</title>
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

                <!-- ------------------------- FORM GOAL ------------------------- -->

                    <form action="<%= request.getContextPath()%>/insert-activity?userId=<%= request.getParameter("userId")%>&name=<%= request.getParameter("name")%>" method="post">

                        <!-- ---------------- FORM PAGE ACTION NAME ---------------- -->

                        <caption>
                            <h2>Add New Activity</h2>
                        </caption>

                        <!-- ------------------- USER ID ------------------- -->

                        <fieldset class="form-group">
                            <label>User ID</label>
                            <input type="number"
                                   value="<%= request.getParameter("userId")%>"
                                   min="0"
                                   class="form-control"
                                   name="user_id"
                                   required="required" />
                        </fieldset>

                        <!-- --------------- ACTIVITY DESCRIPTION ---------------- -->
                        <fieldset class="form-group">
                            <label>Activity</label>
                            <!-- alternative - replace <input> field by <textbox> -->
                            <input type="text"
                                   class="form-control"
                                   name="activity" />
                        </fieldset>

                        <!-- ------------------- DURATION ------------------- -->
                        <fieldset class="form-group">
                            <label>Duration</label>
                            <input type="number"
                                   min = 0
                                   step = 0.1
                                   class="form-control"
                                   name="duration">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
            </div>
        </div>
    </div>
</body>
</html>