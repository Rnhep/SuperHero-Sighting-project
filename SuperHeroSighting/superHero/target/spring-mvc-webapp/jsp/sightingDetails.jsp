<%-- 
    Document   : SightingDetails
    Created on : Nov 2, 2017, 10:07:44 PM
    Author     : ritheenhep
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SuperHeroSighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 id="h1"> Sighitng Detail </h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperPeoplePage">
                            Add Heros or Villains
                        </a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displaySightingPage">
                            Add Sighting
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationPage">
                            Add Location
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Add Organization
                        </a>
                    </li>
                </ul>    
            </div>
        </div>
        <div class="container"  id="table">

            <table id="contactTable" class="table table-hover" >
                <tr>
                    <th width="10%">Date</th>
                    <th width="10%">Who was there</th>
                    <th width="10%">Location Seen</th>
                    <th width="20%">Description</th>
                    <th width="30%"></th>
                </tr>
                <tr>
                    <td>
                        <c:out value="${sighting.date}"/>   

                    </td>
                    <td>
                        <c:forEach var="currentPeople" items="${sighting.superPerson}">
                            <c:out value="${currentPeople.name}"/>,

                        </c:forEach>
                    </td>
                    <td>
                        <c:out value="${sighting.location.locationName}"/>,
                    </td>
                    <td>
                        ${sighting.description}
                    </td>
                    <td>

                    </td>

                </tr>

            </table>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
