<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.fdmgroup.model.Student" %>
<%@ page import = "com.fdmgroup.model.Librarian" %>

<html>
<head>
<!--  comment -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title> Search Result </title>
		<link href = "resources/css/style.css" rel = "stylesheet" type = "text/css">
	<link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
	<link rel="stylesheet" 
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" 
	crossorigin="anonymous">
</head>

<body>
	<h1> 
	<!-- menu option (different for each type of user -->
		<% if (request.getSession().getAttribute("loggedInUser") instanceof Student) {%>
			<%@ include file = "StudentHeader.html" %>
		<% } %>
		
		<% if (request.getSession().getAttribute("loggedInUser") instanceof Librarian) {%>
			<%@ include file = "LibrarianHeader.html" %>
		<% } %>
	</h1>

<!--  search bar  -->
	<div id = "searchblock">
		<form action = "search" method = "POST">
			<input class ="searchbar" placeholder="Search by title" name = "title">
			<input type = "submit" class = "submitbutton" id = "searchbutton" value = "Enter">
		</form>
		<a id = "advsearchtext" href="advancedSearch">
			Advanced Search
		</a>
		<a id = "showallbookstext" href="showAll">
			Show All Books
		</a>
	</div>

<!--  search result text -->
<div class = "subsection" style = "margin: 20px 15%; font-size: 30px; color: black;">
	Search Result:
</div>	

<!-- search results -->	

<c:forEach items = "${searchResult}" var = "book">
	<div class = "searchresultblock">
		<img class = "bookthumbnail" src = "resources/img/${book.isbn}.jpg"></img>
		<div class = "bookdetails">
			<p> Title: ${book.title} </p>
			<p> Author: ${book.author} </p>
			<p> ISBN: ${book.isbn} </p>
			<p> Availability: ${book.availability} </p>
		</div>
		<div class = "bookopt">
			<div>
				<div>
					<form action = "bookprofile" method = "POST" >
						<input type = "hidden" name = "book" value = "${book.isbn}">
						<input class = "reserve" type = "submit" value = "Details">
					</form>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
			

<script src="resources/js/modal.js"></script>
</body>
</html>