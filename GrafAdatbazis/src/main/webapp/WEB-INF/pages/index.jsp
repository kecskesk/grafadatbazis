<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>	
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/bootstrap.min.css" rel="stylesheet">
        <link href="resources/style.css" rel="stylesheet">
        <title>Train schedule manager</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
        <script src="http://momentjs.com/downloads/moment.js"></script>
        <script type="text/javascript">
            var first = 0;
            var max = 10;
            $(document).ready(function () {
                refreshData();
            });
            
            function refreshData() {
                $('#searchForm').submit(function (event) {
                    loadPage();
                    event.preventDefault();
                });
            }
            
            function loadPage(){
                var origin = $('#origin').val();
                var destination = $('#destination').val();
                var startTime = new Date($('#startTime').val());
                var endTime = new Date($('#endTime').val());
                var json = {
                    "schedule" : {"id": "", "originId": origin, "destinationId": destination, "startTime": startTime, "endTime": endTime}, 
                    "paging" : {"max": max, "first": first}
                };

                $.ajax({
                    url: "searchSchedule",
                    data: JSON.stringify(json),
                    type: "POST",
                    headers: { 
                        'Accept': 'application/json',
                        'Content-Type': 'application/json' 
                    },
                    success: function (data) {
                        $("#parent").find("tr").each(function(){ 
                        	$(this).hide() 
                        });                        
                        var respContent = "";
                        data.forEach(function (entry, index) {
                            respContent += "<tr id='result'>";
                            respContent += "<td>" + (first + index + 1) + "</td>";
                            respContent += "<td>" + entry.origin.stationName + "</td>";
                            respContent += "<td>" + entry.destination.stationName + "</td>";
                            respContent += "<td>" + moment(entry.startTime).format('YYYY-MM-DD HH:mm') + "</td>";
                            respContent += "<td>" + moment(entry.endTime).format('YYYY-MM-DD HH:mm') + "</td>";
                        });
                        $("#resultPanel").show();
                        $("#result").replaceWith(respContent);
                        $("#searchFields").hide();
                        
                        if (respContent === '') {
                            prev();
                        }
                    }
                });
            }
            
            function next() {
                first = first + max;
                loadPage();
            }
            
            function prev() {
                if (first - max >= 0) {
                    first = first - max;
                    loadPage();
                }
            }
        </script>
    </head>
    <body>
        <div id="wrap">
            <c:import url="navbar.jsp"/>
            <div class="container">
                <div class="panel panel-primary" id="searchFields">
                    <div class="panel-heading">
                        <h3 class="panel-title pull-left">Schedule Search</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel-body">
                        <form:form action="searchSchedule" id="searchForm" method="post" modelAttribute="schedule">
                            <div class="form-group">
                                <label for="origin">Origin:</label>
                                <form:select id="origin" 
                                             class="form-control"
                                             path="originId" >
                                    <form:option  value="0">--Select one--</form:option>  
                                    <form:options items="${listStation}" itemValue="id" itemLabel="stationName" />                         
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label for="destination">Destination:</label>
                                <form:select id="destination"
                                             class="form-control"
                                             path="destinationId" >
                                    <form:option  value="0">--Select one--</form:option>  
                                    <form:options items="${listStation}" itemValue="id" itemLabel="stationName" />  
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label for="startTime">Start time: </label>
                                <form:input id="startTime" 
                                            class="form-control"
                                            path="startTime" 
                                            placeholder="yyyy-MM-dd"/>
                            </div>
                            <div class="form-group">
                                <label for="endTime">End time: </label>
                                <form:input id="endTime" 
                                            class="form-control"
                                            path="endTime" 
                                            placeholder="yyyy-MM-dd"/>
                            </div>
                            <div>
                                <button class="btn btn-default" type="submit">Search</button>
                            </div>
                        </form:form>
                    </div>
                </div>
                <div class="panel panel-primary" id="resultPanel" hidden>
                    <div class="panel-heading">
                        <h3 class="panel-title pull-left">Results</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table" id="parent">
                                <th>#</th>
                                <th>Origin</th>
                                <th>Destination</th>
                                <th>Start time</th>
                                <th>End time</th>
                                <tr id="result">
                                
                                </tr>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-2">
                                <button class="btn btn-primary" onclick="prev()">< Prev</button>
                            </div>
                            
                            <div class="col-md-2">
                                <button class="btn btn-primary" onclick="next()">Next ></button>
                            </div>
                            <div class="col-md-4"></div>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
