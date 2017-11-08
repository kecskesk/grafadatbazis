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
        <title>New/Edit User</title>
    </head>
    <body>
        <div id="wrap">
            <c:import url="navbar.jsp"/>
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title pull-left">New/Edit User</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel-body">
                        <form:form action="saveUser" method="post" modelAttribute="user">
                            <form:hidden path="id"/>
                            <div class="form-group">
                                <label for="username">Username:</label>
                                <form:input class="form-control" id="username" path="username" />
                            </div>
                            <div class="form-group">
                                <label>Role:</label>
                                <br/>
                                <label class="radio-inline"><form:radiobutton path="roleText" value="ROLE_ADMIN" />ADMIN</label>
                                <label class="radio-inline"><form:radiobutton path="roleText" value="ROLE_USER"/>USER</label> 
                            </div>
                            <div class="form-group">
                                <label>Password:</label>
                                <form:input class="form-control" id="password" type="password" path="password" />
                            </div>
                            <div class="form-group">
                                <button class="btn btn-default" type="submit" >Save</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>