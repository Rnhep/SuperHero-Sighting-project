<%-- 
    Document   : Location
    Created on : Oct 25, 2017, 10:11:47 PM
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Location</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>

        <div class="container" id="title">
            <h1 id="h1">Super Heros Sighting</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>
                    <li role="presentation" >
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
        <div class="container-fluid"  id="table">
            <h2 id="h1">Enter Location</h2>
            <table id="contactTable" class="table table-hover" >
                <tr>
                    <th width="10%">Location Name</th>
                    <th width="10%">Address</th>
                    <th width="10%">Latitude</th>
                    <th width="10%">Longitude</th>
                    <th width="10%">Description</th>
                    <th width="5%"></th>
                    <th width="5%"></th>
                    <th width="5%"></th>
                </tr>

                <c:forEach var="currentLocation" items="${locationList}">
                    <tr>
                        <td>
                            <a href="displayLocationDetails?locationId=${currentLocation.locationId}">
                                <c:out value="${currentLocation.locationName}"/>:    
                            </a>
                        </td>
                        <td>
                            <c:out value="${currentLocation.street}"/>
                            <c:out value="${currentLocation.city}"/>
                            <c:out value="${currentLocation.state}"/>
                            <c:out value="${currentLocation.zip}"/>
                        </td>

                        <td >
                        
                        </td>

                        <td  >
                             
                        </td>

                        </form>
                        <td >
                            <c:out value="${currentLocation.description}"/>
                        </td>
                        <td>
                            <a href="displayEditLocationForm?locationId=${currentLocation.locationId}">
                                <button class="btn-sm" > Edit</button>
                            </a>
                        </td>
                        <td>
                            <a href="deleteLocation?locationId=${currentLocation.locationId}" class="delete" data-confirm="Are you sure to delete this location?">
                                <button class="btn-danger">  Delete</button>
                            </a>
                        </td>
                        <td>

                        </td>
                    </tr>

                </c:forEach>

            </table>
        </div>
        <button id="button" onclick="addlocation()"> Add location</button>   
        <div class="container col-md-12" id="toHide">
            <h2 id="loc">Add Location</h2>
            <form name="location" class="form-horizontal" 
                  role="form"  method="POST" 
                  action="addLocation" >
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="locationName" class="col-md-6 control-label">Location Name</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="locationName" placeholder="Location Name" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="street" class="col-md-6 control-label">Street</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="street" placeholder="Street" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="city" class="col-md-6 control-label">City</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="city" placeholder="city" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="state" class="col-md-6 control-label">state</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="state" placeholder="state" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="zip" class="col-md-6 control-label">zip</label>
                        <div class="col-md-3">
                            <input type="text" pattern="[0-9]*"  class="form-control" name="zip" placeholder="zip" required/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="latitude" class="col-md-6 control-label">Latitude</label>
                        <div class="col-md-3">
                            <input type="text" pattern="-?[0-9]+" step=".01"min="1" max="10" class="form-control" name="latitude" placeholder="latitude"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="longitude" class="col-md-6 control-label">Longitude</label>
                        <div class="col-md-3">
                            <input type="text" pattern="-?[0-9]+" step=".01" class="form-control" name="longitude" placeholder="lognitude" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-md-6 control-label">Description</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="description" placeholder="Description"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-6 col-md-6">
                            <input type="submit" id="add"class="btn btn-default" value="Add Location"/>
                        </div>
                    </div>  
                </div>
            </form>
          
        </div>
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
