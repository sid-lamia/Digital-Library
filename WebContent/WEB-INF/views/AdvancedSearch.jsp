<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.fdmgroup.model.Student" %>
<%@ page import = "com.fdmgroup.model.Librarian" %>

<html>
<head>
	<title> Advanced Search </title>
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
<div id = "searchblock">
	<div style= "height: 50%; width: 90%; margin-top:75px; margin-left:5%">
		<div class = "dropdownbtn" id = "dropdownoptions">
			<p style = "white-space: nowrap; margin:0px;">Search Criteria</p>
			<div id = "dropdowncontent">
				<a class="dropLink2 optiontext" onclick = "changeoption1()">Genre</a>
				<a class="dropLink2 optiontext" onclick = "changeoption2()">Author</a>
				<a class="dropLink2 optiontext" onclick = "changeoption3()">Keyword</a>
			</div>
		</div>
		<script src="resources/js/dropdown.js"></script>
		<form action = "advancedSearch" method = "POST">
			<input id ="advsearchbar" placeholder="Search" name = "searchTitle">
			<input type = "hidden" id = "searchtype" name = "searchCriteria">
			<input type = "submit" class = "submitbutton" id = "advsearchbutton" value = "Enter">
		</form>
	</div>	
</div>	
</body>
</html>