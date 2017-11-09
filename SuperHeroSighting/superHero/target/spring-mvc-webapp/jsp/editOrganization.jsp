<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <title>Company Contact</title>
    </head>
    <body>
        <div class="container">
            <h1>SUPER HERO SIGHTING</h1>
            <hr/>
            <div class="container">
                <h1 id="Superheroheading"> Edit Organization</h1>
                <hr/>
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation" >
                            <a href="${pageContext.request.contextPath}/">Home</a></li>
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
            <div class="row">
                <div class="col-md-6">
                    <sf:form class="form-horzontal" role="form" modelAttribute="organization"
                             action="editOrganization" method="POST">
                        <div class="form-group">
                            <label for="date" class="col-md-4 control-label">Current Organization</label>

                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="name" path="name"
                                          placeHolder="name"/>
                                <sf:errors path="name" cssClass="error"></sf:errors>
                                </div>
                                <div class="form-group">
                                    <label for="phone" class="col-md-4 control-label">Current Phone</label>
                                    <div class="col-md-8">
                                    <sf:input type="text" class="form-control" 
                                              id="phone" path="phone"
                                              placeHolder="phone"/>
                                    <sf:errors path="phone" cssClass="error"></sf:errors>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="description" class="col-md-4 control-label">Current Description</label>
                                    <div class="col-md-8">
                                    <sf:input type="text" class="form-control" 
                                              id="description" path="desciption"
                                              placeHolder="description"/>
                                    <sf:errors path="desciption" cssClass="error"></sf:errors>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="location" class="col-md-4 control-label">Current Location</label>
                                    <div class="col-md-1">
                                        <div class="dropdown">
                                            <select name="location.locationId" required>
                                            <c:forEach var="currentLocation" items="${locationList}">
                                                <option value="${currentLocation.locationId}"${currentLocation.locationId == organization.location.locationId? "selected" : " " }>
                                                    <c:out value="${currentLocation.locationName}"/>
                                                </c:forEach>

                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">

                                    <input type="submit" class="btn btn-default" value="Update Organization"/>
                                </div>
                            </div>
                            <sf:hidden path="organizationId"/>
                        </sf:form> 
                    </div>
                </div>
                <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

                </body>
                </html>
