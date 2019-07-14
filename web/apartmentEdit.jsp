<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: pote
  Date: 03/07/2019
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% String checked = null; %>
<%String language= (String) session.getAttribute("language");%>
<fmt:setLocale value="<%=language%>" scope="session"/>
<fmt:setBundle basename="Messages" var="labels" scope="session"/>
<jsp:useBean id="apartmentSelected" scope="session" type="it.uniroma2.dicii.ispw.fersa.Bean.ApartmentBean"/>

<head>
    <title><fmt:message key="apartment.edit" bundle="${labels}"/> </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='js/jquery-1.8.3.min.js'></script>
    <script src="js/bootstrap.bundle.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/bootstrap-grid.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-reboot.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.min.css" rel="stylesheet">
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
                    <a class="dropdown-item" href="languageServlet?page=apartmentEdit.jsp&lang=it" ><span class="flag-icon flag-icon-it"></span><fmt:message key="italian" bundle="${labels}"/> </a>
                    <a class="dropdown-item" href="languageServlet?page=apartmentEdit.jsp&lang=en" ><span class="flag-icon flag-icon-us"></span><fmt:message key="english" bundle="${labels}"/></a>
                </div>
            </li>
        </div>
    </div>
</nav>
    <div class="container">
        <div class="row">
            <form action="bedServlet" method="get">
                <div class="col-sm">
                    <h1>Servizi appartamento</h1>
                    <select name="heating" class="custom-select" >
                        <option value="1" <c:if test="${apartmentSelected.heating_type == 'apartmentEdit.heatingType.Indipendent'}"> <c:out value="selected"/></c:if>><fmt:message key="apartmentEdit.heatingType.Indipendent"  bundle="${labels}"/></option>
                        <option value="2" <c:if test="${apartmentSelected.heating_type == 'apartmentEdit.heatingType.Shared'}"> <c:out value="selected"/></c:if>><fmt:message key="apartmentEdit.heatingType.Shared" bundle="${labels}"/></option>
                    </select>

                    <%
                        if (apartmentSelected.getAirConditioning()) {
                            checked = "checked='true'";
                        }else{
                            checked = "";
                        }%>
                    <div class="custom-control custom-checkbox">
                        <input value="true" type="checkbox" name="airConditioning" <%=checked%> class="custom-control-input" id="aircontioning">
                        <label class="custom-control-label" for="aircontioning"><fmt:message key="apartmentEdit.airconditioning" bundle="${labels}"/> </label>
                    </div>


                    <%
                        if (apartmentSelected.getTv()){
                            checked = "checked='true'";
                        }else{
                            checked = "";
                        }%>
                    <div class="custom-control custom-checkbox">
                        <input value="true" type="checkbox" name="tv" <%=checked%> class="custom-control-input" id="tv">
                        <label class="custom-control-label" for="tv"><fmt:message key="apartmentEdit.tv" bundle="${labels}"/>  </label>
                    </div>


                    <%
                        if (apartmentSelected.getShared_room_space()){
                            checked = "checked='true'";
                        }else{
                            checked = "";
                        }%>
                    <div class="custom-control custom-checkbox">
                        <input value="true" type="checkbox" <%=checked%> name="sharedRoom" class="custom-control-input" id="sharedRoom">
                        <label class="custom-control-label" for="sharedRoom"><fmt:message key="apartmentEdit.sharedRoom" bundle="${labels}"/> </label>
                    </div>

                    <%
                        if (apartmentSelected.getWifi()){
                            checked = "checked='true'";
                        }else{
                            checked = "";
                        }%>
                    <div class="custom-control custom-checkbox">
                        <input value="true" type="checkbox" <%=checked%> name="wifi" class="custom-control-input" id="wifi">
                        <label class="custom-control-label" for="wifi"><fmt:message key="apartmentEdit.wifi" bundle="${labels}"/></label>
                    </div>
                    <%
                        if (apartmentSelected.getFurnished()){
                            checked = "checked='true'";
                        }else{
                            checked = "";
                        }%>

                    <div class="custom-control custom-checkbox">
                        <input value="true" type="checkbox" <%=checked%> name="furnished" class="custom-control-input" id="furnished">
                        <label class="custom-control-label" for="furnished"><fmt:message key="apartmentEdit.Furnished" bundle="${labels}"/> </label>
                    </div>
                    <%
                        if (apartmentSelected.getWashingMachine()){
                            checked = "checked='true'";
                        }else{
                            checked = "";
                        }%>
                    <div class="custom-control custom-checkbox">
                        <input value="true" type="checkbox" <%=checked%> name="washingMachine" class="custom-control-input" id="washingMachine">
                        <label class="custom-control-label" for="washingMachine"><fmt:message key="apartmentEdit.washingMachine" bundle="${labels}"/> </label>
                    </div>
                    <%
                        if (apartmentSelected.getDryer()){
                            checked = "checked='true'";
                        }else{
                            checked = "";
                        }%>

                    <div class="custom-control custom-checkbox">
                        <input value="true" type="checkbox" <%=checked%> name="dryer" class="custom-control-input" id="dryer">
                        <label class="custom-control-label" for="dryer"><fmt:message key="apartmentEdit.dryer" bundle="${labels}"/> </label>
                    </div>

                    <%
                        if (apartmentSelected.getDishWasher()){
                            checked = "checked='true'";
                        }else{
                            checked = "";
                        }%>
                    <div class="custom-control custom-checkbox">
                        <input value="true" type="checkbox" <%=checked%> name="dishWasher" class="custom-control-input" id="dishWasher">
                        <label class="custom-control-label" for="dishWasher"><fmt:message key="apartmentEdit.dishWasher" bundle="${labels}"/> </label>
                    </div>


                    <label>
                        <select name="utilityBills" class="custom-select" >
                            <option value="1" <c:if test="${apartmentSelected.utilityBillsType == 'apartmentEdit.condominiumFee.Included'}"> <c:out value="selected"/></c:if>><fmt:message key="apartmentEdit.condominiumFee.Included"  bundle="${labels}"/></option>
                            <option value="2" <c:if test="${apartmentSelected.utilityBillsType == 'apartmentEdit.condominiumFee.NotIncluded'}"> <c:out value="selected"/></c:if>><fmt:message key="apartmentEdit.condominiumFee.NotIncluded" bundle="${labels}"/></option>
                        </select>
                    </label>

                    <select name="rentType" class="custom-select" >
                        <option value="1" <c:if test="${apartmentSelected.rentType == 'apartmentEdit.rentType.allApartmentRent'}"> <c:out value="selected"/></c:if>><fmt:message key="apartmentEdit.rentType.allApartmentRent"  bundle="${labels}"/></option>
                        <option value="2" <c:if test="${apartmentSelected.rentType == 'apartmentEdit.rentType.roomRent'}"> <c:out value="selected"/></c:if>><fmt:message key="apartmentEdit.rentType.roomRent" bundle="${labels}"/></option>
                        <option value="3" <c:if test="${apartmentSelected.rentType == 'apartmentEdit.rentType.notSpecified'}"> <c:out value="selected"/></c:if>><fmt:message key="apartmentEdit.rentType.notSpecified" bundle="${labels}"/></option>
                    </select>

                    <label for="condominiumFee"><fmt:message key="apartmentEdit.condominiumFee" bundle="${labels}"/> </label>
                    <input type="number" name="condominiumFee" class="form-control" id="condominiumFee" value="${apartmentSelected.condominiumFee}" placeholder="<fmt:message key="apartmentEdit.condominiumFee" bundle="${labels}"/>" aria-describedby="button-addon4">

                    <label for="rentalFee"><fmt:message key="apartmentEdit.rentalFee" bundle="${labels}"/> </label>
                    <input type="number" name="rentalFee" class="form-control" id="rentalFee" value="${apartmentSelected.rentalFee}" placeholder="<fmt:message key="apartmentEdit.rentalFee" bundle="${labels}"/>" aria-describedby="button-addon4">


                    <div class="w-100"></div>
                    <div class="col">
                        <button type="submit" class="btn btn-primary" style="float: right"><fmt:message key="button.submit" bundle="${labels}"/></button>
                    </div>
                </div>
            </form>
            <div class="col-sm">
                <h1>Configura letti</h1>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th><fmt:message key="room.number" bundle="${labels}"/></th>
                            <th><fmt:message key="room.type" bundle="${labels}"/></th>
                            <th><fmt:message key="room.fee" bundle="${labels}"/></th>
                            <th><fmt:message key="bed.number" bundle="${labels}"/></th>
                            <th><fmt:message key="bed.fee" bundle="${labels}"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${apartmentSelected.beds}" var="bed">
                            <c:set var="bedsNumber" scope="request" value="${bed.room.beds.size()}"/>
                            <tr onclick="window.location.href='/FERSA_jsp_war_exploded/apartmentEdit.jsp?bedBeanId=${bed.id}';">
                            <th>${bed.room.roomNumber}</th>
                            <%if ((Integer)request.getAttribute("bedsNumber") > 1){%>
                            <td><fmt:message key="room.type.multiple" bundle="${labels}"/></td>
                            <%}else{%>
                            <td><fmt:message key="room.type.single" bundle="${labels}"/></td>
                            <%}%>
                            <td>${bed.room.fee}</td>
                            <td>${bed.bedNumber}</td>
                            <td>${bed.fee}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                <form action="bedServlet" method="get">
                    <div class="input-group">
                            <input type="number" name="bedFee" class="form-control" placeholder="<fmt:message key="apartmentEdit.insert.bedFee" bundle="${labels}"/>"aria-describedby="button-addon4">
                            <div class="input-group-append" id="button-addon4">
                               <input type="text" hidden value="<%= request.getParameter("bedBeanId")%>" name="bedBeanId">
                                <button name="addButton" class="btn btn-primary"  type="submit" style="float: right"><fmt:message key="button.add" bundle="${labels}"/></button>
                                <button name="deleteButton" class="btn btn-danger" type="submit" style="float: right"><fmt:message key="button.delete" bundle="${labels}"/></button>
                            </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
