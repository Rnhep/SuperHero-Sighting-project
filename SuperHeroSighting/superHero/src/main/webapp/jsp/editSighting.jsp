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
                <h1 id="Superheroheading">Edit Sighting</h1>
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
            <div class="row">
                <div class="col-md-6">
                    <sf:form class="form-horzontal" role="form" modelAttribute="sighting"
                             action="editSighting" method="POST">

                        <div class="form-group">
                            <label for="date" class="col-md-4 control-label">Current Date</label>

                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="date" path="date"
                                          placeHolder="date"/>
                                <sf:errors path="date" cssClass="error"></sf:errors>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="date" class="col-md-4 control-label">Current Description</label>

                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="date" path="description"
                                          placeHolder="description"/>
                                <sf:errors path="description" cssClass="error"></sf:errors>

                                </div>

                            </div>
                            <div class="form-group">
                                <label for="side" class="col-md-4 control-label">Current Location</label>
                                <div class="col-md-1">
                                    <div class="dropdown">
                                        <select name="location.locationId"  value="Selectlocation">
                                        <c:forEach var="currentLocation" items="${locationList}">
                                            <option value="${currentLocation.locationId}"${currentLocation.locationId == sighting.location.locationId? "selected" : " " }>
                                                <c:out value="${currentLocation.locationName}"/>:
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="col-md-8">
                                <label for="sel2">Mutiple select list (hold shift to select more than one):<br/>
                                    Current Heros/Villains</label>
                                <select multiple class="form-control" id="sel2" name="superPersonId" required>
                                    <c:forEach var="currentHeroAndVillains" items="${heroList}">
                                        <option  value="${currentHeroAndVillains.superPersonId}"
                                                 <c:forEach var="currentPeople" items="${sighting.superPerson}">
                                                     <c:if test="${currentHeroAndVillains.superPersonId == currentPeople.superPersonId}">
                                                         selected
                                                     </c:if>
                                                 </c:forEach>>

                                            <c:out  value="${currentHeroAndVillains.name}"/>

                                        </option>
                                    </c:forEach>
                                </select>
                            </div>      

                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <sf:hidden path="sightingId"/>
                                <input type="submit" class="btn btn-default" value="Update superPerson"/>
                            </div>
                        </div>
                    </sf:form> 
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

