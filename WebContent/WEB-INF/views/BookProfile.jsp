<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.fdmgroup.model.Student" %>
<%@ page import = "com.fdmgroup.model.Librarian" %>
<%@ page import = "com.fdmgroup.model.Book" %>

<html>
<head>
	<title> Book Profile </title>
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
<p style = "margin-left:9%; color:red;"> ${cannotreserve}</p>

	<img class = "bookimagesize" src = "resources/img/${book.isbn}.jpg" style = "border: 2px solid darkgray; margin-top: 30px;"></img>
	<div class = "box1">
		<p class = "profiletext">Title</p>		
		<p class = "profiletext">Author</p>		
		<p class = "profiletext">ISBN</p>
		<p class = "profiletext">Genre</p>
		<p class = "profiletext">Number of Pages</p>
		<p class = "profiletext">Year Published</p>
		<p class = "profiletext">Availability</p>		
		<p class = "profiletext" style = "height: 185px;">Summary</p>
		
	</div>
	<div class = "box2">
		<p class = "profiletextinfo"> ${book.title}</p>		
		<p class = "profiletextinfo">${book.author}</p>		
		<p class = "profiletextinfo">${book.isbn}</p>
		<p class = "profiletextinfo">${book.genre} </p>
		<p class = "profiletextinfo">${book.numberOfPages}</p>
		<p class = "profiletextinfo">${book.publishedYear}</p>
		<p class = "profiletextinfo">${book.availability} </p>	
		<p class = "profiletextinfo" style = "height: 185px;">${book.summary}</p>
	</div>
<c:if test = "${ book.availability == 'Available'}">
	<div id = "buttoncontainer">
			<button class = "reserve" onclick = "showModal(reservemodal)">
			Reserve</button>
		</div>
</c:if>
</div>

<div>
	<div class="modal" id="reservemodal">
		<div class="modal-content">
			<span id="close" onclick="closeModal(reservemodal)" >
			<i class="fas fa-window-close closeiconstyle" ></i>
			</span>
			<p class = "modaltext"> Are you sure you want to reserve this book?</p>
				<form style = "display: inline;" action = "reserve" method = "POST">
					<input type = "hidden" value = "${book.isbn}"  name = "reservebook">
					<input class = "yesbutton" type = "submit" value = "Yes">
				</form>
				<button class= "nobutton" onclick="closeModal(reservemodal)">
					No
				</button>			
		</div>
	</div>
</div>
<script src="resources/js/modal.js"></script>

</body>
</html>