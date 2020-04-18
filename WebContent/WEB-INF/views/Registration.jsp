<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title> Register </title>
	<link href = "resources/css/style.css" rel = "stylesheet" type = "text/css">
	<link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
	<link rel="stylesheet" 
			href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
			integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" 
			crossorigin="anonymous">
</head>

<body>
	<h1>
		<a href = "home" id = "headingtext"> Bookworm </a>	
	</h1>	
	
	<div class = "homesubsection">
		<p style = "text-align: center;">Welcome to Bookworm library! Please log in with your email and password, or click register to create an account.</p>
		<div id = "logreg">
			<a style = "text-decoration: none; color: white;" href="home">
				<div class = "lr">
					Login
				</div>
			</a>
			<div class = "lr highlighted">
				Register
			</div>
		</div>
		<form id = "loginform" action = "register" method = "POST">
			<input class ="forminput" placeholder="First Name" name = "firstname">
			<input class ="forminput" placeholder="Last Name" name = "lastname">
			<input class ="forminput" placeholder="Email" name = "email">
			<input class ="forminput" input type = "Password" placeholder="Password" name = "password">
			<input class ="forminput" placeholder="Grade" name = "grade">
			<input class = "submitbutton" id = "loginbutton" type = "submit" value = "Register" onclick = "showModal(reservedmodal)">
			<p style = "margin-left: 9%; color:red;"> ${emptyfieldmsg}</p>
			<p style = "margin-left: 9%;margin-right: 9%; text-align: center;"> ${registeredmsg}</p>
			
		</form>
	</div>	
	
</body>
</html>