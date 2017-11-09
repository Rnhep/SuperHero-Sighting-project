<%-- 
    Document   : locationDetails
    Created on : Nov 2, 2017, 11:00:32 PM
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
            <h1 id="h1"> Location Detail </h1>
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingPage">
                            Add Sighting
                        </a>
                    </li>
                    <li role="presentation" class="active">
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
                    <th width="10%">Location Name</th>
                    <th width="10%">Latitude</th>
                    <th width="10%">Longitude</th>
                    <th width="50">Address</th>
                    <th width="20%"></th>
                    <th width="30%"></th>
                </tr>
                <tr>
                    <td>
                        <c:out value="${location.locationName}"/>     
                    </td>
                    <td>
                        <input id="lat" value="${location.latitude}" />
                    </td>
                    <td>
                        <input id="lng" value="${location.longitude}"/>
                    </td>
                    <td>
                        <c:out value="${location.street}"/>
                        <c:out value="${location.city}"/>
                        <c:out value="${location.state}"/>
                        <c:out value="${location.zip}"/>
                    </td>
                    <td>
                        <div class="container" id="map" style="width: 250px; height: 250px;"></div>
                    </td>
                    <td>

                    </td>

                </tr>

            </table>
        </div>
<!--        <div class="container" id="map" style="width: 250px; height: 250px;"></div>-->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAeDowzm3Wu5fVED_t_yz0D23DFS7PJ-eo&callback=initMap">
        </script>
        <script src="javascripts/jquery.js"></script>
        <script type="text/javascript" src="javascripts/jquery.googlemap.js"></script>

    </body>
</html>