<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
       
<!--        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAeDowzm3Wu5fVED_t_yz0D23DFS7PJ-eo"></script>
        <script type="text/javascript" src="javascripts/jquery.googlemap.js"></script> -->
    </head>
    <body>
        <div class="container">
            <h1 id="h1">SUPER HERO SIGHTING</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Add Organization
                        </a>
                    </li>
                </ul>    
            </div>
            <div class="container-fluid" style="border: solid 1px #f0f0f5 ">
                <h2 style="text-align: center">Latest Sightings</h2>
                <table id="contactTable" class="table table-hover" >
                    <tr>
                        <th width="10%">Latest Date</th>
                        <th width="10%">Location</th>
                        <th width="50">Super Human</th>
                        <th width="20%">Description</th>
                        <th width="30%"></th>
                    </tr>
                    <c:forEach var="sightings" items="${sightingList}">
                        <tr>
                            <td>
                                <c:out value="${sightings.date}"/>   
                            </td>
                            <td>
                                <c:out value="${sightings.location.locationName}"/>
                            </td>
                            <td>
                                <c:forEach var="currentPeople" items="${sightings.superPerson}">
                                    <c:out value="${currentPeople.name}"/>,
                                </c:forEach>
                            </td>
                            <td>
                                <c:out value="${sightings.description}"/>
                            </td>


                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>

                            



        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
          <script src="javascripts/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>
       


    </body>
</html>

