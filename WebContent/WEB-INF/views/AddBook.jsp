<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.fdmgroup.model.Student" %>
<%@ page import = "com.fdmgroup.model.Librarian" %>

<html>
<head>
	<title> Add Books </title>
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
	
	
<div class = "homesubsection">
	<div style = "margin-right:20%; margin-top: 30px;">
	<p style = "text-align:center">Please enter the following details</p>
	</div>
	<form id = "addbookform" action = "addbook" method = "POST">
		<input class ="forminput" placeholder="Title" name = "title">
		<input class ="forminput" placeholder="Author" name = "author">
		<input class ="forminput" type = "number" placeholder="ISBN" name = "isbn">
		<input class ="forminput" placeholder="Genre" name = "genre">
		<input class ="forminput" type = "number" placeholder="Number of Pages" name = "pages">
		<input class ="forminput" placeholder="Published Year" name = "year">
		<input class ="forminput" placeholder="Summary" name = "summary">
		
		<div class = "submitbutton" id = "addbookbtn" onclick = "showModal(addbookmodal)">
			Add Book
		</div>
		<p style = "margin-left:9%; color:red;"> ${emptyfieldmsg}</p>
		
		<div class="modal" id="addbookmodal">
			<div class="modal-content">
				<span id="close" onclick="closeModal(addbookmodal)" >
				<i class="fas fa-window-close closeiconstyle" ></i>
				</span>
				<p class = "modaltext"> Are you sure you want add this book to the database?</p>
					<input class= "yesbutton" type= "submit" name = "add">
					<button class= "nobutton" onclick="closeModal(addbookmodal)">
						NO
					</button>			
			</div>
		</div>
	</form>
</div>

<script src="resources/js/modal.js"></script>

</body>
</html>
