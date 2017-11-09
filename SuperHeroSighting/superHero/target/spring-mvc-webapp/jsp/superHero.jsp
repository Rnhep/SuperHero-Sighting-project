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
        <title>SuperHeroSighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>

        <div class="container">
            <h1 id="h1"> Super Hero Sighting </h1>
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
                            Add Organization
                        </a>
                    </li>
                </ul>    
            </div>
        </div>
        <div class="container"  id="table">
            <h2 style="text-align: center">Super Hero and super Villains</h2>
            <table id="contactTable" class="table table-hover" >
                <tr>
                    <th width="10%">Name</th>
                    <th width="10%">Power</th>
                    <th width="40%">Description</th>
                    <th width="5%"></th>
                    <th width="5%"></th>
                </tr>
                <c:forEach var="currentHeroAndVillains" items="${heroList}">
                    <tr>
                        <td>
                            <a href="displaySuperPersonDetails?superPersonId=${currentHeroAndVillains.superPersonId}">
                                <c:out value="${currentHeroAndVillains.name}"/>   
                            </a>
                        </td>
                        <td>
                            <c:out value="${currentHeroAndVillains.power}"/>
                        </td>
                        <td>
                            <c:out value="${currentHeroAndVillains.description}"/>
                        </td>
                        <td>
                            <a href="displayEditSuperPersonForm?superPersonId=${currentHeroAndVillains.superPersonId}">
                                <button class="btn-sm" > Edit</button>
                            </a>
                        </td>
                        <td>
                            <a  href="deleteSuperPerson?superPersonId=${currentHeroAndVillains.superPersonId}" class="delete" data-confirm="Are you sure to delete this person?">
                                <button class="btn-danger">  Delete</button>
                            </a>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </div>
        <button id="button" onclick="addlocation()"> Add Hero</button>
        <div class="container col-md-9" id="toHide">

            <h2 id="h1">Add Super Hero or Super Villains</h2>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="addSuperPerson" >
                <div class="form-group">
                    <label for="heroname" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-3">
                        <input type="text" class="form-control" name="name" placeholder="Name" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="power" class="col-md-4 control-label">Power:</label>
                    <div class="col-md-3">
                        <input type="text" class="form-control" name="power" placeholder="Power"required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-3">
                        <input type="text" class="form-control" name="description" placeholder="description"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="side" class="col-md-4 control-label">Hero/Villain:</label>
                    <div class="col-md-8">
                        <input type="checkbox" name="side" value="true" checked="checked"/> Un-check for Villain
                    </div>
                </div>
                <div class="form-group">
                    <label for="location" class="col-md-4 control-label">organization</label>
                    <div class="col-md-1">
                        <div class="dropdown">
                            <select multiple name="organization"  value="Select organization" required>
                                <c:forEach var="currentOrg" items="${organizationList}">
                                    <option   value="${currentOrg.organizationId}">
                                        <c:out value="${currentOrg.name}"/>
                                    </option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add Hero"/>
                    </div>
                </div>
            </form>
        </div>



        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>
    </body>
</html>
