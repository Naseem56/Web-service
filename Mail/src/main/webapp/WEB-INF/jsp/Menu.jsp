<%@page import="com.messenger.bean.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
<style>

.sidenav {
    height: 100%;
    width: 20%;
    position: fixed;
    top: 0;
    left: 0;
    background-color: #111;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 50px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
}

.sidenav a:hover, .offcanvas a:focus{
    color: #f1f1f1;
}

#main {
    margin-left:21%;
}

.avatar{
	margin-left:40px;
	width: 60%;
    border-radius: 50%;

}
.welcome{
	color:#4e2d8e;
	text-align:center;
	font-size:30px;
	margin-left:50px;
}

.name{
	font-size:20px;
	color: #c50303;
	display:block;
	margin-left:50px;
	}
</style>
</head>
<body>

<% Profile prf = (Profile)request.getAttribute("prf"); 
	request.setAttribute("prf", prf);
%>
<div id="mySidenav" class="sidenav">
  <img src="./resources/images/profile1.jpg" alt="Avatar" class="avatar">
  <br>
  <span class="welcome">Welcome</span>
  <br>
  <span class="name"></span>
  <br>
    <a href="http://localhost:8080/Mail/Compose.html">Compose Mail</a>
   <a href="http://localhost:8080/Mail/Inbox.html">Inbox</a>
   <a href="http://localhost:8080/Mail/Outbox.html">Outbox</a>
   <a href="http://localhost:8080/Mail/Contacts.html">Contacts</a>
   <a href="http://localhost:8080/Mail/Logout.html">Log out</a>
</div>
<div id="main">

<h2>Welcome to mail <%out.print(prf.getfName()); %> <%out.print(prf.getlName()); %></h2>

</div>

</body>



</html> 