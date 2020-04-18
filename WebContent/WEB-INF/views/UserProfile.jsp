<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.fdmgroup.model.Student" %>
<%@ page import = "com.fdmgroup.model.Librarian" %>
<%@ page import = "com.fdmgroup.model.Book" %>
<%@ page import = "java.util.List" %>
<html>
<head>
	<title> User Profile </title>
	<link href = "resources/css/style.css" rel = "stylesheet" type = "text/css">
	<link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
	<link rel="stylesheet" 
		href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
		integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" 
		crossorigin="anonymous">
</head>

<body>
	<h1>
		<% if (request.getSession().getAttribute("loggedInUser") instanceof Student) {%>
			<%@ include file = "StudentHeader.html" %>
		<% } %>
		
		<% if (request.getSession().getAttribute("loggedInUser") instanceof Librarian) {%>
			<%@ include file = "LibrarianHeader.html" %>
		<% } %>	
	</h1>
	
	
<div class = "container">
<div style = "width: 45%; margin:0px 2.5%; height: 100%; display: inline; float: left;">
	<img class = "imagesize" src = "resources/img/usericon.png" style = "border: 2px solid darkgray;"></img>
	<div class = "box3">
	<div class = "box4">
		<p class = "profiletext">Name</p>		
		<p class = "profiletext">ID</p>		
		<p class = "profiletext">Username</p>
	</div>
	<div class = "box5">
		<p class = "profiletextinfo">${loggedInUser.firstname} ${loggedInUser.lastname} </p>		
		<p class = "profiletextinfo">${loggedInUser.id}</p>		
		<p class = "profiletextinfo">${loggedInUser.username}</p>
	</div>
	</div>
</div>
<div style = "width:45%; margin:345px 2.5% 0px 2.5%; height: 100%; display: inline; float: right;">
	<div class = "box4">
		<p class = "profiletext">Books Reserved</p>		
		<p class = "profiletext">Books Overdue</p>		
		<p class = "profiletext">Overdue Fee</p>
	</div>
	<div class = "box5">
		<p class = "profiletextinfo">${listSize}</p>		
		<p class = "profiletextinfo">${overdueCount}</p>		
		<p class = "profiletextinfo">$${totalFee}</p>
	</div>
	</div>
</div>			
</body>