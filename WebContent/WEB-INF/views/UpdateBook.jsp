<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.fdmgroup.model.Student" %>
<%@ page import = "com.fdmgroup.model.Librarian" %>

<html>
<head>
	<title> Update Books </title>
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
<form action = "updatesearch" method = "POST">
		<input type = "number" class ="searchbar" placeholder="Search by isbn" name = "isbn">
		<input type = "submit" class = "submitbutton" id = "searchbutton" value = "Enter">
</form>
		<a id = "advsearchtext" href="showallreserved">
			Show All Reserved Books
		</a>
		<a id = "showallbookstext" href="showallavailable">
			Show All Available Books
		</a>
</div>
<p style = "margin-left: 15%;"> ${nosuchbookmsg}</p>

<c:forEach items = "${foundBook}" var= "book"> 
<c:if test = "${ book != null}">
	<div class = "searchresultblock" style = "height: 400px;">
		<img class = "bookthumbnail" src = "resources/img/${book.isbn}.jpg"></img>
		<div class = "bookdetails">
			<p> Title: ${book.title} </p>
			<p> Author: ${book.author} </p>
			<p> ISBN: ${book.isbn} </p>
			<p> Availability: ${book.availability} </p>
			<p> Current User Id: ${book.reserveStatus.currentUser.id} </p>
			<p> Date Borrowed: ${book.reserveStatus.borrowDate} </p>
			<p> Expected Return Date: ${book.reserveStatus.returnDate} </p>
			<p> Overdue Fee: $${book.reserveStatus.overdueFee} </p>
		</div>
		<div class = "bookopt">
			<div>
				<button class = "reserve" onclick = "showModal(addbookmodal)">
					Remove</button>
			</div>
			<div>
				<button class = "reserve" onclick = "showModal(reservemodal)">
					Update</button>
			</div>
		</div>

	<div class="modal" id="addbookmodal">
		<div class="modal-content">
			<span id="close" onclick="closeModal(addbookmodal)" >
			<i class="fas fa-window-close closeiconstyle" ></i>
			</span>
			<p class = "modaltext"> Are you sure you want remove this book from the database?</p>
				<form style = "display: inline;" action = "remove" method = "POST">
				<input type = "hidden" value = "${book.isbn}"  name = "removebook">
				<input class = "yesbutton" type = "submit" value = "Yes">
				</form>
				<button class= "nobutton" onclick="closeModal(addbookmodal)">
					No
				</button>			
		</div>
	</div>
<div class="modal" id="reservemodal">
		<div class="modal-content">
			<span id="close" onclick="closeModal(reservemodal)" >
			<i class="fas fa-window-close closeiconstyle" ></i>
			</span>
			<p class = "modaltext"> The current user, borrow date, and return date will be set to null. Continue?</p>
				<form style = "display: inline;" action = "update" method = "POST">
				<input type = "hidden" value = "${book.isbn}" name = "updatebook">
				<input class = "yesbutton" type = "submit" value = "Yes">
				</form>
				<button class= "nobutton" onclick="closeModal(reservemodal)">
					No
				</button>			
		</div>
	</div>	
</div>
</c:if>
</c:forEach>		
<script src="resources/js/modal.js"></script>

</body>
</html>