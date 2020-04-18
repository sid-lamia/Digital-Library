<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<link href="resources/css/style.css" rel="stylesheet" type="text/css">
	<link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
</head>

<body>
	<h1>
		<a href="home" id ="headingtext"> Bookworm </a>	
	</h1>	
	<div class = "homesubsection">
		<p style = "text-align: center;">Welcome to Bookworm library! Please log in with your email and password, or click register to create an account.</p>
		<div id = "logreg">
			<div class = "lr highlighted">
				Login
			</div>
			<a style = "text-decoration: none; color: white;" href="register">
				<div class = "lr">
					Register
				</div>
			</a>
		</div>
		<form id = "loginform" action = "authenticate" method = "POST" >
			<input class ="forminput" type = "text" placeholder="Email" name = "email">
			<input class ="forminput" type = "password" placeholder="Password" name = "password">
			<input class = "submitbutton" id = "loginbutton" type = "submit" value = "Login">
			<p class = "errormessage" style = "margin-left:9%; color: red;"> ${errorMsg}</p>
		</form>	
	</div>
</body>
</html>