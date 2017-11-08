<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../resources/bootstrap.min.css" rel="stylesheet">
        <link href="../resources/style.css" rel="stylesheet">
        <title>New/Edit Schedule</title>
    </head>
    <body>
        <div id="wrap">
            <c:import url="navbar.jsp"/>
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title pull-left">New/Edit Schedule</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel-body">
                        <form:form action="saveSchedule" method="post" modelAttribute="schedule">
                            <form:hidden path="id"/>
                            <div class="form-group">
                                <label for="origin">Origin:</label>
                                <form:select id="origin" 
                                             class="form-control"
                                             path="originId" 
                                             items="${listStation}" 
                                             itemValue="id" 
                                             itemLabel="stationName" />
                            </div>
                            <div class="form-group">
                                <label for="destination">Destination:</label>
                                <form:select id="destination"
                                             class="form-control"
                                             path="destinationId" 
                                             items="${listStation}" 
                                             itemValue="id" 
                                             itemLabel="stationName" />
                            </div>
                            <div class="form-group">
                                <label for="startTime">Start time: </label>
                                <form:input id="startTime" 
                                            class="form-control"
                                            path="startTime" 
                                            placeholder="yyyy-MM-dd HH:mm"/>
                            </div>
                            <div class="form-group">
                                <label for="endTime">End time: </label>
                                <form:input id="endTime" 
                                            class="form-control"
                                            path="endTime" 
                                            placeholder="yyyy-MM-dd HH:mm"/>
                            </div>
                            <div>
                                <button class="btn btn-default" type="submit">Save</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>