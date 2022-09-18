
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

                <!-- ---------------------- FORM GOAL LOGIC ---------------------- -->

                <c:if test="${activity != null}">
                    <form action="<%= request.getContextPath()%>/update-activity" method="post">
                </c:if>
                <c:if test="${activity == null}">
                    <form action="<%= request.getContextPath()%>/insert-activity" method="post">
                </c:if>

                        <!-- ------------------- FORM NAME LOGIC ------------------- -->

                        <caption>
                            <h2>
                                <c:if test="${activity != null}">Edit Activity</c:if>
                                <c:if test="${activity == null}">Add New Activity</c:if>
                            </h2>
                        </caption>

                        <!-- -------- HIDE "USER ID" IF IT IS USER EDIT ACTION -------- -->

                        <c:if test="${activity != null}">
                            <input type="hidden" name="id" value="<c:out value='${activity.id}' />" />
                        </c:if>

                        <!-- ------------------- USER ID ------------------- -->
                        <fieldset class="form-group">
                            <label>User ID</label>
                            <input type="number"
                                   value="<c:out value='${activity.user_id}' />"
                                   min="0"
                                   class="form-control"
                                   name="user_id"
                                   required="required" />
                        </fieldset>

                        <!-- --------------- ACTIVITY DESCRIPTION ---------------- -->
                        <fieldset class="form-group">
                            <label>Activity</label>
                            <input type="text"
                                   value="<c:out value='${activity.activity}' />"
                                   class="form-control"
                                   name="activity" />
                        </fieldset>

                        <!-- ------------------- DURATION ------------------- -->
                        <fieldset class="form-group">
                            <label>Duration</label>
                            <input type="number"
                                   min = 0
                                   step = 0.01
                                   value="<c:out value='${activity.duration}' />"
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