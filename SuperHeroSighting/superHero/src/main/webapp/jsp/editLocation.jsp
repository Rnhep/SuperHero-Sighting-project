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
        <title></title>
    </head>
    <body>
        <div class="container">
            <h1>SUPER HERO SIGHTING</h1>
            <hr/>
            <div class="container">
                <h1 id="Superheroheading"> Edit Location</h1>
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
                        <li role="presentation" 
                            class="active">
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
                    <sf:form class="form-horzontal" role="form" modelAttribute="location"
                             action="editLocation" method="POST">

                        <div class="form-group">
                            <label for="location-name" class="col-md-4 control-label">Location Name</label>

                            <div class="col-md-8">
                                <sf:input type="text"  class="form-control" 
                                          id="locationName" path="locationName"
                                          placeHolder="name"/>
                                <sf:errors path="locationName" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="street" class="col-md-4 control-label">Street</label>

                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="street" path="street"
                                          placeHolder="street"/>
                                <sf:errors path="street" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="city" class="col-md-4 control-label">city</label>

                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="city" path="city"
                                          placeHolder="city"/>
                                <sf:errors path="city" cssClass="error"></sf:errors>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="state" class="col-md-4 control-label">state</label>

                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="state" path="state"
                                          placeHolder="state"/>
                                <sf:errors path="state" cssClass="error"></sf:errors>
                                </div>
                            </div>  
                            <div class="form-group">
                                <label for="zip" class="col-md-4 control-label">zip</label>

                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="zip" path="zip"
                                          placeHolder="zip"/>
                                <sf:errors path="zip" cssClass="error"></sf:errors>
                                </div>
                            </div>  
                            <div class="form-group">
                                <label for="latitude" class="col-md-4 control-label">latitude</label>

                                <div class="col-md-8">
                                <sf:input   type="number"  step="0.00000" min="-90" max="90"  class="form-control" 
                                          id="latitude" path="latitude"
                                          placeHolder="latitude"/>
                                <sf:errors path="latitude" cssClass="error"></sf:errors>
                                </div>
                            </div>  
                            <div class="form-group">
                                <label for="longitude" class="col-md-4 control-label">longitude</label>

                                <div class="col-md-8">
                                <sf:input type="number" step="0.00000" min="-190" max="190" class="form-control" 
                                          id="longitude" path="longitude"
                                          placeHolder="longitude"/>
                                <sf:errors path="longitude" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-md-4 control-label">description</label>

                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="description" path="description"
                                          placeHolder="description"/>
                                <sf:errors path="description" cssClass="error"></sf:errors>
                                <sf:hidden path="locationId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Update Location"/>
                            </div>
                        </div>
                    </sf:form> 
                </div>
            </div>


            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
