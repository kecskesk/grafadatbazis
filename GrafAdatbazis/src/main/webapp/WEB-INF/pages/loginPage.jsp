<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login</title>
<link href="resources/bootstrap.min.css" rel="stylesheet">
<link href="resources/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="navbar.jsp" />
	<!-- /login?error=true -->
	<c:if test="${param.error == 'true'}">
		<div class="alert alert-danger">
			<strong>Login Failed!</strong> Reason :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title pull-left">Login</h3>
				<div class="clearfix"></div>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<form name='f'
						action="${pageContext.request.contextPath}/j_spring_security_check"
						method='POST'>
							<div class="form-group">
								<label for="username">User:</label>
								<input class="form-control" type='text' name='username' value=''>
							</div>
							<div class="form-group">
								<label for="password">Password:</label>
								<input class="form-control" type='password' name='password' />
							</div>
							<div class="form-group">
								<input class="btn btn-default" name="submit" type="submit" value="submit" />
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>