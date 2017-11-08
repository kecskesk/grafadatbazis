<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/bootstrap.min.css" rel="stylesheet">
        <link href="resources/style.css" rel="stylesheet">
        <title>Station Manager</title>
    </head>
    <body>
        <div id="wrap">
            <c:import url="navbar.jsp"/>
            <div class="container">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title pull-left">Station List</h3>
                        <a type="button" class="btn btn-default pull-right" href="station/newStation">New Station</a>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table">
                                <th>#</th>
                                <th>Station name</th>
                                <th>Action</th>

                                <c:forEach var="station" items="${listStation}" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${station.stationName}</td>
                                        <td>
                                            <a type="button" class="btn btn-sm btn-default" href="station/editStation?id=${station.id}">Edit</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a type="button" class="btn btn-sm btn-danger" 
                                               onclick="return confirm('Are you sure you want to delete?')"  
                                               href="station/deleteStation?id=${station.id}">
                                                Delete
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>	        	
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
