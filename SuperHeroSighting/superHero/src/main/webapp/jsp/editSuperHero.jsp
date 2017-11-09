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
                <h1 id="Superheroheading"> Edit Supers</h1>
                <hr/>
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"> 
                            <a href="${pageContext.request.contextPath}/">
                                Home
                            </a>
                        </li>
                        <li role="presentation"
                            class="active">
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
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                                Add Location
                            </a>
                        </li>
                    </ul>    
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <sf:form class="form-horzontal" role="form" modelAttribute="superPerson" 
                             action="editSuperPerson" method="POST">

                        <div class="form-group">
                            <label for="name"  class="col-md-4 control-label">Name</label>

                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="name"
                                          id="name" path="name"
                                          placeHolder="name"/>
                                <sf:errors path="name" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-md-4 control-label">Power</label>

                                <div class="col-md-8">
                                <sf:input type="text" name="power" class="form-control" 
                                          id="power" path="power"
                                          placeHolder="power"/>
                                <sf:errors path="power" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="company-name"class="col-md-4 control-label">Description</label>

                                <div class="col-md-8">
                                <sf:input type="text"  name="discription" class="form-control" 
                                          id="description" path="description"
                                          placeHolder="description"/>
                                <sf:errors path="description" cssClass="error"></sf:errors>
                                <sf:hidden path="superPersonId"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="location" class="col-md-4 control-label">organization</label>
                            <div class="col-md-7">
                                <div class="dropdown">
                                    <select multiple name="organization"  value="Select organization" required>
                                        <c:forEach var="currentOrg" items="${organizationList}">
                                            <option   modelAttribute="organization" value="${currentOrg.organizationId}">
                                                <c:out value="${currentOrg.name}"/>
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="side" class="col-md-4 control-label">Hero/Villain:</label>

                            <div class="col-md-8">
                                <input type="checkbox" name="side" value="true" checked="checked"/> Un-check for Villain
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
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
