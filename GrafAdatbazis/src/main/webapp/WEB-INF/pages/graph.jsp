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

<title>Graph Manager</title>
</head>
<script type="text/javascript">     
        function edit(id) {
            $.ajax({
                url: "graph/getGraph",
                data: {"id": id},
                type: "GET",
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                success: function (data) {
                	var graph = data;
                	$("#id").val(data.id);
                	$("#name").val(data.name);
                	$("#descriptor").val(data.descriptor);
                	
                    $("#graphEdit").show();
                    $("#graphList").hide();
                }
            });
        }

		$(document).ready(function () {
			$('#editForm').submit(function (event) {
                event.preventDefault();
				var graph = {};
	        	graph['id'] = $("#id").val();
	        	graph['name'] = $("#name").val();
                graph['descriptor'] = $("#descriptor").val();
	        		        	
	            $.ajax({
	                url: "graph/saveGraph",
	                data: JSON.stringify(graph),
	                type: "POST",
	                headers: { 
	                    'Accept': 'application/json',
	                    'Content-Type': 'application/json' 
	                },
	                success: function (data) {
						console.log(data);
	                	
	                    $("#graphEdit").hide();
	                    $("#graphList").show();
	                    $('#graphList').load(document.URL +  ' #graphList');
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
					<h3 class="panel-title pull-left">Graph List</h3>
					<div class="pull-right">
						<a type="button" class="btn btn-default" href="graph/newGraph">New Graph</a>
						<a type="button" class="btn btn-default" style="" href="graph/upload">Upload File</a>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="panel-body">
					<div id="graphList" class="table-responsive">
						<table class="table">
							<th>#</th>
							<th>Name</th>
							<th>Descriptor</th>
							<th>Action</th>

							<c:forEach var="graph" items="${listGraph}"
								varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td>${graph.name}</td>
									<td>${graph.descriptor}</td>
									<td>
										<a type="button" 
												class="btn btn-sm btn-default" 
												onclick="edit(${graph.id})">Edit</a>
										&nbsp;&nbsp;&nbsp;&nbsp; 
										<a type="button"
												class="btn btn-sm btn-danger"
												href="graph/deleteGraph?id=${graph.id}"
												onclick="return confirm('Are you sure you want to delete?')">
											Delete 
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div id="graphEdit" hidden>
						<div id="editFields">
							<form:form id="editForm" modelAttribute="newGraph">
	                            <form:hidden path="id"/>
								<div class="form-group">
									<label for="name">Name:</label>
									<form:input class="form-control" id="name" path="name" />
								</div>
								<div class="form-group">
									<label for="descriptor">Descriptor:</label>
									<form:input class="form-control" id="descriptor" path="descriptor" />
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

