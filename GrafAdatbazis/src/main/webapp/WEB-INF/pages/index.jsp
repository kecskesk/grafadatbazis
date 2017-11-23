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
        <title>Graph manager</title>
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
                var name = $('#name').val();
                var descriptor = $('#descriptor').val();
                var json = {
                    "graph" : {"id": "", "name": name, "descriptor": descriptor},
                    "paging" : {"max": max, "first": first}
                };

                $.ajax({
                    url: "searchGraph",
                    data: JSON.stringify(json),
                    type: "POST",
                    headers: { 
                        'Accept': 'application/json',
                        'Content-Type': 'application/json' 
                    },
                    success: function (data) {
                        $("#parent").find("tr").each(function(index, element){
                            if (element.id !== 'header') {
                                $(this).hide();
                            }
                        });
                        var respContent = "";
                        data.forEach(function (entry, index) {
                            respContent += "<tr id='result'>";
                            respContent += "<td>" + (first + index + 1) + "</td>";
                            respContent += "<td>" + entry.name + "</td>";
                            respContent += "<td>" + entry.descriptor + "</td>";
                            respContent += "</tr>";
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
            <!-- /login?error=true -->
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    <strong>Oops! Something went wrong...</strong> There was an error of class: ${param.errorClass}
                    <c:if test="${param.errorMessage != null}">
                            with message: ${param.errorMessage}
                    </c:if>
                </div>
            </c:if>

            <div class="container">
                <div class="panel panel-primary" id="searchFields">
                    <div class="panel-heading">
                        <h3 class="panel-title pull-left">Graph Search</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel-body">
                        <form:form action="searchGraph" id="searchForm" method="post" modelAttribute="graph">
                            <div class="form-group">
                                <label for="name">Name:</label>
                                <form:input class="form-control" id="name" path="name" />
                            </div>
                            <div class="form-group">
                                <label for="descriptor">Descriptor:</label>
                                <form:input class="form-control" id="descriptor" path="descriptor" />
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
                                <tr id="header">
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Descriptor</th>
                                </tr>
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
