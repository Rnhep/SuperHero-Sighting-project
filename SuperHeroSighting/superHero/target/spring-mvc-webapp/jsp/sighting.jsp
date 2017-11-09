<%-- 
    Document   : sighting
    Created on : Oct 25, 2017, 10:14:26 PM
    Author     : ritheenhep
--%>
<%-- 
    Document   : superHero
    Created on : Oct 25, 2017, 10:09:44 PM
    Author     : ritheenhep
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
        <div class="container"id="title">
            <h1 id="h1"> Super Hero Sighting </h1>
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
                            Add  Organization
                        </a>
                    </li>
                </ul>    
            </div>
        </div>
        <div class="container " id="table">
            <h1 id="h1">Sighting</h1>
            <table id="contactTable" class="table table-hover" >
                <tr>
                    <th width="20%">Date</th>
                    <th width="20%">Location</th>
                    <th width="10">Description</th>
                    <th width="20%">Who Was There</th>
                    <th width="5%"></th>
                    <th width="5%"></th>
                </tr>
                <c:forEach var="currentSighting" items="${sightingList}">
                    <tr>
                        <td>
                            <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                <c:out value="${currentSighting.date}"/>:    
                            </a>
                        </td>
                        <td>
                            <c:out value="${currentSighting.location.locationName}"/>
                        </td>
                        <td>
                            <c:out value="${currentSighting.description}"/>
                        </td>
                        <td>
                            <c:forEach var="currentPeople" items="${currentSighting.superPerson}">
                                <c:out value="${currentPeople.name}"/>

                            </c:forEach>
                        </td>
                        <td>
                            <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                <button class="btn-sm" > Edit</button>
                            </a>
                        </td>
                        <td>
                            <a href="deleteSighting?sightingId=${currentSighting.sightingId}" class="delete" data-confirm="Are you sure to delete this sighting?">
                                <button class="btn-danger">  Delete</button>
                            </a>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </div>
        <button id="button" onclick="addlocation()"> Add Sighting</button>
        <div class="container col-md-9" id="toHide" >

            <h2 id="h1">Add Sighting</h2>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="addSighting" >
                <div class="form-group">
                    <label for="date" class="col-md-4 control-label">Date:</label>
                    <div class="col-md-3">
                        <input type="date" class="form-control" name="date" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-3">
                        <input type="text" class="form-control" name="description" placeholder="description"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="side" class="col-md-4 control-label">location</label>
                    <div class="col-md-1">
                        <div class="dropdown">
                            <select name="location"  value="Select-location" required>
                                <c:forEach var="currentLocation" items="${locationList}">
                                    <option  value="${currentLocation.locationId}">
                                        <c:out value="${currentLocation.locationName}"/>:
                                    </option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="hero" class="col-md-4 control-label">Hero</label>
                    <div class="col-md-3">
                        <label for="sel2">Mutiple select list (hold shift to select more than one):</label>
                        <select multiple class="form-control" id="sel2" name="superPersonId" required>
                            <c:forEach var="currentHeroAndVillains" items="${heroList}" >
                                <option name="supers" value="${currentHeroAndVillains.superPersonId}"/>
                                <c:out value=" ${currentHeroAndVillains.name}"/>
                                </option>

                            </c:forEach>
                        </select>
                    </div>      
                </div>

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add Sighting"/>
                    </div>
                </div>

            </form>
        </div>



        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>

    </body>
</html>
