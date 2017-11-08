<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/bootstrap.min.css" rel="stylesheet">
<link href="resources/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://momentjs.com/downloads/moment.js"></script>

<title>Schedule Manager</title>
</head>
<script type="text/javascript">     
        function edit(id) {
            $.ajax({
                url: "schedule/getSchedule",
                data: {"id": id},
                type: "GET",
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                success: function (data) {
                	var schedule = data;
                	$("#id").val(data.id);
                	$("#origin").val(data.originId);
                	$("#destination").val(data.destinationId);
                	
                	$("#startTime").val(new Date(data.startTime).toUTCString());
                	$("#endTime").val(new Date(data.endTime).toUTCString());
                	
                    $("#scheduleEdit").show();
                    $("#scheduleList").hide();
                }
            });
        }

		$(document).ready(function () {
			$('#editForm').submit(function (event) {
                event.preventDefault();
				var schedule = {};
	        	schedule['id'] = $("#id").val();
	        	schedule['originId'] = $("#origin").val();
	        	schedule['destinationId'] = $("#destination").val();
	        	schedule['startTime'] = $("#startTime").val();
	        	schedule['endTime'] = $("#endTime").val();
	        		        	
	            $.ajax({
	                url: "schedule/saveSchedule",
	                data: JSON.stringify(schedule),
	                type: "POST",
	                headers: { 
	                    'Accept': 'application/json',
	                    'Content-Type': 'application/json' 
	                },
	                success: function (data) {
						console.log(data);
	                	
	                    $("#scheduleEdit").hide();
	                    $("#scheduleList").show();
	                    $('#scheduleList').load(document.URL +  ' #scheduleList');
	                }
	            });
		    });
		});
</script>
<body>
	<div id="wrap">
		<c:import url="navbar.jsp" />
		<div class="container">

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title pull-left">Schedule List</h3>
					<a type="button" class="btn btn-default pull-right" href="schedule/newSchedule">New Schedule</a>
					<div class="clearfix"></div>
				</div>
				<div class="panel-body">
					<div id="scheduleList" class="table-responsive">
						<table class="table">
							<th>#</th>
							<th>Origin</th>
							<th>Destination</th>
							<th>Start time</th>
							<th>End time</th>
							<th>Action</th>

							<c:forEach var="schedule" items="${listSchedule}"
								varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td>${schedule.origin.stationName}</td>
									<td>${schedule.destination.stationName}</td>
									<td><fmt:formatDate value="${schedule.startTime}" pattern="${datePattern}" /></td>
									<td><fmt:formatDate value="${schedule.endTime}" pattern="${datePattern}" /></td>
									<td>
										<a type="button" 
												class="btn btn-sm btn-default" 
												onclick="edit(${schedule.id})">Edit</a>
										&nbsp;&nbsp;&nbsp;&nbsp; 
										<a type="button"
												class="btn btn-sm btn-danger"
												href="schedule/deleteSchedule?id=${schedule.id}"
												onclick="return confirm('Are you sure you want to delete?')">
											Delete 
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div id="scheduleEdit" hidden>
						<div id="editFields">
							<form:form id="editForm" modelAttribute="newSchedule">
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

		</div>
	</div>
</body>
</html>

