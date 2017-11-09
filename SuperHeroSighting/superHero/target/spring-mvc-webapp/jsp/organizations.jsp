<%-- 
    Document   : Organizations
    Created on : Oct 25, 2017, 10:13:22 PM
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
            <h1 id="h1">Super Hero Sighting</h1>
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationPage">
                            Add Location
                        </a>
                    </li>
                    <li role="presentation"  class="active">
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Add Organization
                        </a>
                    </li>
                </ul>    
            </div>
        </div>

        <div class="container"  id="table">
            <h1 id="h1">Organization</h1>
            <table id="contactTable" class="table table-hover" >
                <tr>
                    <th width="15%">Name</th>
                    <th width="30%">Descriptio</th>
                    <th width="5%"></th>
                    <th width="5%"></th>
                </tr>
                <c:forEach var="currentOrganization" items="${OrganizationList}">
                    <tr>
                        <td>
                            <a href="displayOrganizationDetails?organizationId=${currentOrganization.organizationId}">
                                <c:out value="${currentOrganization.name}"/>:    
                            </a>
                        </td>
                        <td>
                            <c:out value="${currentOrganization.desciption}"/>
                        </td>
                        <td>
                            <a href="displayEditOrganizationForm?organizationId=${currentOrganization.organizationId}">
                                <button class="btn-sm" > Edit</button>
                            </a>
                        </td>
                        <td>
                            <a  href="deleteOrganization?organizationId=${currentOrganization.organizationId}" class="delete" data-confirm="Are you sure to delete this organization?">
                                <button class="btn-danger">  Delete</button>
                            </a>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </div>
        <button id="button" onclick="addlocation()"> Add location</button>
        <div class="container col-md-9" id="toHide" >
            <h2 id="h1">Add Organization</h2>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="addOrganization" >
                <div class="form-group">
                    <label for="organization" class="col-md-4 control-label">Organization:</label>
                    <div class="col-md-3">
                        <input type="text" class="form-control" name="name" placeholder="Organizaiton Name" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-md-4 control-label">Phone:</label>
                    <div class="col-md-3">
                        <input type="text" pattern="[0-9]*" class="form-control" name="phone" placeholder="phone" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="discription" class="col-md-4 control-label">Description</label>
                    <div class="col-md-3">
                        <input type="text" class="form-control" name="description" placeholder="description" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="location" class="col-md-4 control-label">location</label>
                    <div class="col-md-1">
                        <div class="dropdown">
                            <select name="location"  value="Select location" required>
                                <c:forEach var="currentLocation" items="${locationList}">
                                    <option   value="${currentLocation.locationId}">
                                        <c:out value="${currentLocation.locationName}"/>:

                                    </option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add Organization"/>
                    </div>
                </div>
            </form>
        </div>



        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>

    </body>
</html>
