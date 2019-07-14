<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pote
  Date: 06/07/2019
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%String language= (String) session.getAttribute("language");%>
<fmt:setLocale value="<%=language%>" scope="session"/>
<fmt:setBundle basename="Messages" var="labels" scope="session"/>
<jsp:useBean id="apartmentContracts" scope="session" type="org.apache.commons.collections4.BidiMap<it.uniroma2.dicii.ispw.fersa.Bean.ContractBean,it.uniroma2.dicii.ispw.fersa.Bean.ApartmentBean>"/>
<jsp:useBean id="roomContracts" scope="session" type="org.apache.commons.collections4.BidiMap<it.uniroma2.dicii.ispw.fersa.Bean.ContractBean,it.uniroma2.dicii.ispw.fersa.Bean.RoomBean>"/>
<jsp:useBean id="bedContracts" scope="session" type="org.apache.commons.collections4.BidiMap<it.uniroma2.dicii.ispw.fersa.Bean.ContractBean,it.uniroma2.dicii.ispw.fersa.Bean.BedBean>"/>

<head>
    <title><fmt:message key="contract.panel" bundle="${labels}"/> </title>
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

    <%String message = (String)session.getAttribute("alertMsg");
        session.removeAttribute("alertMsg");
        if(message != null){%>

    <script type="text/javascript">
        var msg = "<%=message%>";
        alert(msg);
    </script>
    <%} %>
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
                        <a class="dropdown-item" href="languageServlet?page=contractPanel.jsp&lang=it" ><span class="flag-icon flag-icon-it"></span><fmt:message key="italian" bundle="${labels}"/> </a>
                        <a class="dropdown-item" href="languageServlet?page=contractPanel.jsp&lang=en" ><span class="flag-icon flag-icon-us"></span><fmt:message key="english" bundle="${labels}"/></a>
                    </div>
                </li>
            </div>
        </div>
    </nav>
    <div class="container">
        <h1><fmt:message key="contract.type.apartment" bundle="${labels}"/> </h1>
        <div class="row">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="contract.residence" bundle="${labels}"/></th>
                    <th><fmt:message key="contract.renter" bundle="${labels}"/></th>
                    <th><fmt:message key="contract.startDate" bundle="${labels}"/> </th>
                    <th><fmt:message key="contract.endDate" bundle="${labels}"/> </th>
                    <th><fmt:message key="contract.state" bundle="${labels}"/> </th>
                </tr>
                </thead>
                <c:forEach items="${apartmentContracts}" var="entry">
                    <tr onclick="window.location.href='/FERSA_jsp_war_exploded/contractPanel.jsp?rentableid=${entry.value.id}&fee=${entry.value.fee}';">
                        <td>${entry.value.address}</td>
                        <td><c:out value="${entry.key.renter.username}"/></td>
                        <td><tags:localDate date="${entry.key.start_date}"/></td>
                        <td><tags:localDate date="${entry.key.end_date}"/></td>
                        <td><c:choose>
                            <c:when test='${entry.key.state == "UNAPPROVED"}'><fmt:message key="contract.unapproved" bundle="${labels}"/> </c:when>
                            <c:when test='${entry.key.state == "APPROVED"}'><fmt:message key="contract.approved" bundle="${labels}"/></c:when>
                            <c:otherwise><fmt:message key="contract.waiting" bundle="${labels}"/></c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="row">
            <h1><fmt:message key="contract.type.room" bundle="${labels}"/> </h1>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="contract.residence" bundle="${labels}"/></th>
                    <th><fmt:message key="room.number" bundle="${labels}"/></th>
                    <th><fmt:message key="contract.renter" bundle="${labels}"/></th>
                    <th><fmt:message key="contract.startDate" bundle="${labels}"/> </th>
                    <th><fmt:message key="contract.endDate" bundle="${labels}"/> </th>
                    <th><fmt:message key="contract.state" bundle="${labels}"/> </th>
                </tr>
                </thead>
                <c:forEach items="${roomContracts}" var="entry">
                    <tr onclick="window.location.href='/FERSA_jsp_war_exploded/contractPanel.jsp?rentableid=${entry.value.id}&fee=${entry.value.fee}';">
                        <td>${entry.value.apartment.address}</td>
                        <td><c:out value="${entry.value.roomNumber}"/></td>
                        <td><c:out value="${entry.key.renter.username}"/></td>
                        <td><tags:localDate date="${entry.key.start_date}"/></td>
                        <td><tags:localDate date="${entry.key.end_date}"/></td>
                        <td><c:choose>
                            <c:when test='${entry.key.state == "UNAPPROVED"}'><fmt:message key="contract.unapproved" bundle="${labels}"/> </c:when>
                            <c:when test='${entry.key.state == "APPROVED"}'><fmt:message key="contract.approved" bundle="${labels}"/></c:when>
                            <c:otherwise><fmt:message key="contract.waiting" bundle="${labels}"/></c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="row">
            <h1><fmt:message key="contract.type.bed" bundle="${labels}"/> </h1>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="contract.residence" bundle="${labels}"/></th>
                    <th><fmt:message key="room.number" bundle="${labels}"/></th>
                    <th><fmt:message key="bed.number" bundle="${labels}"/></th>
                    <th><fmt:message key="contract.renter" bundle="${labels}"/></th>
                    <th><fmt:message key="contract.startDate" bundle="${labels}"/> </th>
                    <th><fmt:message key="contract.endDate" bundle="${labels}"/> </th>
                    <th><fmt:message key="contract.state" bundle="${labels}"/> </th>
                </tr>
                </thead>
                <c:forEach items="${bedContracts}" var="entry">
                    <tr onclick="window.location.href='/FERSA_jsp_war_exploded/contractPanel.jsp?rentableid=${entry.value.id}&fee=${entry.value.fee}';">
                        <td>${entry.value.room.apartment.address}</td>
                        <td><c:out value="${entry.value.room.roomNumber}"/></td>
                        <td><c:out value="${entry.value.bedNumber}"/></td>
                        <td><c:out value="${entry.key.renter.username}"/></td>
                        <td><tags:localDate date="${entry.key.start_date}"/></td>
                        <td><tags:localDate date="${entry.key.end_date}"/></td>
                        <td><c:choose>
                            <c:when test='${entry.key.state == "UNAPPROVED"}'><fmt:message key="contract.unapproved" bundle="${labels}"/> </c:when>
                            <c:when test='${entry.key.state == "APPROVED"}'><fmt:message key="contract.approved" bundle="${labels}"/></c:when>
                            <c:otherwise><fmt:message key="contract.waiting" bundle="${labels}"/></c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <form action="contractEditServlet" method="get">
            <div class="input-group">
                <div class="input-group-append" style="float: right" id="button-addon4">
                    <input type="text" hidden value="<%= request.getParameter("rentableid")%>" name="rentableid">
                    <input type="text" hidden value="<%= request.getParameter("fee")%>" name="fee">
                    <button name="editButton" class="btn btn-primary"  type="submit" style="float: right"><fmt:message key="button.edit" bundle="${labels}"/></button>
                    <button name="deleteButton" class="btn btn-danger" type="submit" style="float: right"><fmt:message key="button.delete" bundle="${labels}"/></button>
                </div>
            </div>
        </form>
    </div>

    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>
