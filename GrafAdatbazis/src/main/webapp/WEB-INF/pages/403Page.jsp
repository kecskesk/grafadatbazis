<%@page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Access Denied</title>

<link href="resources/bootstrap.min.css" rel="stylesheet">
<link href="resources/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="navbar.jsp"/>
    <div class="container">
		<div class="alert alert-danger">
			<strong>${message}</strong>
		</div>
	</div>
</body>
</html>