<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pote
  Date: 07/07/2019
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:useBean id="contractSelected" scope="session" type="it.uniroma2.dicii.ispw.fersa.Bean.ContractBean"/>
<head>
    <%String language= (String) session.getAttribute("language");%>
    <fmt:setLocale value="<%=language%>" scope="session"/>
    <fmt:setBundle basename="Messages" var="labels" scope="session"/>
    <title><fmt:message key="contract.edit"/> </title>
    <link rel="stylesheet" href="css/bootstrap-grid.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-reboot.css">
    <link rel="stylesheet" href="css/bootstrap-datepicker3.min.css">
    <script src='js/jquery-1.8.3.min.js'></script>
    <script src='js/bootstrap.bundle.js'></script>
    <script src='js/bootstrap.js'></script>
    <script src='js/bootstrap-datepicker.min.js'></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.min.css" rel="stylesheet">

    <script type='text/javascript'>
        $(function(){
            $('.input-group.date').datepicker({
                format: "yyyy-mm-dd",
                calendarWeeks: true,
                autoclose: true
            });
        });
    </script>
    <script>$('.dropdown-toggle').dropdown()</script>
    <%String message = (String)request.getAttribute("alertMsg");
        if(message != null){%>

    <script type="text/javascript">
        var msg = "<%=message%>";
        alert(msg);
    </script>
    <%} %>

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
                <a class="nav-item nav-link" href="apartmentPanelServlet" aria-disabled="true"><fmt:message key="userPanel.Apartment" bundle="${labels}"/></a>
                <a class="nav-item nav-link" href="contractPanelServlet" aria-disabled="true"><fmt:message key="userPanel.Contract" bundle="${labels}"/></a>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="dropdown09" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="language" bundle="${labels}"/> </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="languageServlet?page=contractEdit.jsp&lang=it" ><span class="flag-icon flag-icon-it"></span><fmt:message key="italian" bundle="${labels}"/> </a>
                        <a class="dropdown-item" href="languageServlet?page=contractEdit.jsp&lang=en" ><span class="flag-icon flag-icon-us"></span><fmt:message key="english" bundle="${labels}"/></a>
                    </div>
                </li>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <div class="col-md">
                    <form action="contractUpdateServlet" method="get">
                        <div class="col-md">
                            <h2><fmt:message key="contract.startDate" bundle="${labels}"/></h2>
                            <div class="input-group date">
                                <input name="startDate" placeholder="Seleziona una data..." value='${contractSelected.start_date}' type="text" class="form-control"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            </div>
                            <h2><fmt:message key="contract.endDate" bundle="${labels}"/></h2>
                            <div class="input-group date">
                                <input name="endDate" placeholder="Seleziona una data..." value='${contractSelected.end_date}' type="text" class="form-control"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                            </div>
                            <h2><fmt:message key="apartmentEdit.rentalFee" bundle="${labels}"/></h2>
                            <input type="number" value='${contractSelected.rentable.fee}' name="rentalFee" class="form-control" placeholder="<fmt:message key="apartmentEdit.rentalFee" bundle="${labels}"/>"aria-describedby="button-addon4">
                            <button name="editButton" class="btn btn-primary" type="submit" style="float: right"><fmt:message key="button.edit" bundle="${labels}"/></button>
                    </form>
                 </div>
            </div>
         </div>
    </div>

</body>
</html>
