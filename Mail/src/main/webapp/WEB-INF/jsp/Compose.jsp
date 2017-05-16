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
	
	.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    overflow: auto;
    height:100px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
	}
	
	.clearfix::after {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    overflow: auto;
    height:100px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}
	
	
</style>

<script>

function showHint(str) {
  var xhttp;
  if (str.length == 0) { 
    document.getElementById("msg").innerHTML = "";
    return;
  }
  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.status == 200) {
      document.getElementById("msg").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "http://192.168.235.173:8080/MessageService/webapi/profile/contacts?mailId="+str, true);
  xhttp.send();   
}

</script>
</head>
<body>

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


<%Profile prf = (Profile)request.getAttribute("prf");

%>





<div id="main">
<h2 >Compose Mail Form</h2>
<form action="/Mail/Compose2.html" method="post">
<table>
<tr><td>From:</td><td><input type="text" name="mailId" value="<%out.print(prf.getMailId());  %>" readonly="readonly"/></td></tr>
<tr><td>To:</td><td><input type="text" name="to" onkeyup="showHint(this.value)"/><div class="clearfix">
        <span id="msg"></span>
        </div></td></tr>



<tr><td>Subject:</td><td><input type="text" name="subject"/></td></tr>
<tr><td colspan="2">Message:</td><td></tr>
<tr><td colspan="2"><textarea name="message" rows="25" cols="70"></textarea></td></tr>
<tr><td colspan="2"><input id="submit" type="submit" />
</td></tr>
</table>
</form>
</div>


</body>
</html>