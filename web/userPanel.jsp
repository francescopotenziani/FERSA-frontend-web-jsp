<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pote
  Date: 27/06/2019
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%String language= (String) session.getAttribute("language");%>
<fmt:setLocale value="<%=language%>" scope="session"/>
<fmt:setBundle basename="Messages" var="labels" scope="session"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><fmt:message key="userPanel" bundle="${labels}"/> </title>
        <script src='js/jquery-1.8.3.min.js'></script>
        <script src="js/bootstrap.bundle.js"></script>
        <script src="js/bootstrap.js"></script>
        <link rel="stylesheet" href="css/bootstrap-grid.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/bootstrap-reboot.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.min.css" rel="stylesheet">
        <script>$('.dropdown-toggle').dropdown()</script>
        <style>
            a {
                color: #92badd;
                display:inline-block;
                text-decoration: none;
                font-weight: 400;
            }

            html {
                background-color: #56baed;
            }

            body {
                font-family: "Poppins", sans-serif;
                height: 100vh;
            }
        </style>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="login.jsp">FERSA</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href="apartmentPanelServlet"><fmt:message key="userPanel.Apartment" bundle="${labels}"/></a>
                <a class="nav-item nav-link" href="contractPanelServlet"><fmt:message key="userPanel.Contract" bundle="${labels}"/></a>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="dropdown09" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="language" bundle="${labels}"/> </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="languageServlet?page=userPanel.jsp&lang=it" ><span class="flag-icon flag-icon-it"></span><fmt:message key="italian" bundle="${labels}"/> </a>
                        <a class="dropdown-item" href="languageServlet?page=userPanel.jsp&lang=en" ><span class="flag-icon flag-icon-us"></span><fmt:message key="english" bundle="${labels}"/></a>
                    </div>
                </li>
            </div>
        </div>
    </nav>
    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/bootstrap.js"></script>
    </body>
</html>
