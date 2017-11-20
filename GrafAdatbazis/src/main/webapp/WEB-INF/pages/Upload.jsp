<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../resources/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/style.css" rel="stylesheet">
    <title>Upload Graphs</title>
</head>
<body>
<div id="wrap">
    <c:import url="navbar.jsp"/>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title pull-left">Upload Graphs</h3>
                <div class="clearfix"></div>
            </div>
            <div class="panel-body">
                <form method="POST" action="saveFromFile" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>File:</label>
                        <input class="form-control" type="file" name="file" />
                    </div>
                    <div>
                        <button class="btn btn-default" type="submit">Upload</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
