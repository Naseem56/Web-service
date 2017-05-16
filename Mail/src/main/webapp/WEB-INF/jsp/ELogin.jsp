<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>GMAIL APPLICATION</title>

</head>
<style>
html, body, div, span,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend {
	background: transparent;
	border: 0;
	margin: 0;
	padding: 0;
	vertical-align: baseline;
	font-family:'Helvetica Neue', Helvetica, Arial, sans-serif;
}
body.login { background: url(vishwa.jpg); }
h2 { font-size: 30px; line-height:38px; font-weight:normal; }
.loginbox { background: #FBFCFD; padding: 10px; width: 490px; margin: 4% auto 0 auto; position: relative; }
.loginboxinner {
	background: #FBFCFD; padding: 5px; position: relative; border: 1px solid #FBFCFD;
	-moz-box-shadow: inset 0 1px 0 #FBFCFD; -webkit-box-shadow: inset 0 1px 0 #FBFCFD; box-shadow: inset 0 1px 0 #FBFCFD;
}
.loginheader { height: 20px; }
.loginform { margin: 4px auto; text-align:center; }

.loginbox h1 { font-size: 30px; letter-spacing: 1px; color: green; font-weight: normal; padding-top: 10px; }
.loginbox p { margin: 7px 0 5px 0; }
.loginbox label { display: block; color: #666; letter-spacing: 1px; font-size: 18px; }
.loginbox input.mini { width:195px; }
.loginbox input {
	padding: 12px 10px; color: #ccc;
	font-family: Arial, Helvetica, sans-serif; margin-top: 8px; font-size: 15px; border: 1px solid #ccc; width: 420px;  outline: none;
}
.loginbox button {
	background: #5870A8 ; background-image:-webkit-linear-gradient(top, #637BAD, #4F67A4); padding: 10px 20px; font-size: 18px; border: 1px solid #1D3871; letter-spacing: 1px; color: #fff; width: 440px; line-height:30px; font-family:	'Helvetica Neue', Helvetica, Arial, sans-serif;
	-moz-box-shadow: 1px 1px 3px #1D3871; -webkit-box-shadow: 1px 1px 3px #1D3871; box-shadow: 1px 1px 3px #1D3871; cursor: pointer;
}

.radius { -moz-border-radius: 5px; -webkit-border-radius: 5px; border-radius: 5px; }
.radius1 { -moz-border-radius: 3px; -webkit-border-radius: 3px; border-radius: 3px; }
.title { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; color:#8E989E; line-height:28px; font-weight:normal; text-align:center; font-size:16px; }

@media screen and (max-width: 430px) {

	body { font-size: 11px; }
	button, input, select, textarea { font-size: 11px; }

	.loginbox { width: auto; margin: 10px; }
	.loginbox input { width: 95%; }
	.loginbox button { width: 100%; }
}

#facebook-Bar {
	background:#45619D;
	border-bottom: 1px solid #0053A6;
	box-shadow: 0 0 2px rgba(0, 0, 0, 0.52);
	min-width: 1000px;
	width: 100%;
	z-index: 1000;
}
#facebook-Frame {
	margin-left:auto;
	margin-right:auto;
	max-width:1000px;
	height:70px;
	display:block;
}
/*-------LOGO-----------*/
#logo {
	float: left;
	height: 70px;
	position: relative;
	width: 160px;
}
#logo a {
	position: absolute;
	top: 20px;
	font-size:23px; font-weight:bold; color:#FFF;
	text-decoration:none;
	font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
}

/*-------Login-----------*/
#header-main-right {
	float: right;
	height: 68px;
	position: relative;
	top:5px;
}
#header-main-right-nav {
	padding: 4px 6px 4px 4px;
	position: absolute;
	right: 0;
}
#login_form .inputtext {
	background-color: #FFFFFF;
	height:16px;
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	font-size: 13px;
	margin: 0;
	padding: 5px 8px 5px 8px;
	width: 166px;
	border:1px solid #3A518A;
}
#login_form .inputtext:hover {
	border-color: #A0A0A0 #B9B9B9 #B9B9B9;
	border-image: none;
	border-style: solid;
	border-width: 1px;
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1) inset;
}

#login_form table tr td {
	padding: 0 0 0 5px;
}
#login_form table tr td label {
	color: #fff;
	cursor: pointer;
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	font-size: 12px;
	font-weight: normal;
	padding-left: 1px;
	text-align: left;
	vertical-align: middle;
}
.fbbutton { border:1px solid #1D3871; width:94px; background-image:-webkit-linear-gradient(top, #637BAD, #4F67A4); font-size:14px; line-height:25px; font-weight:normal; color:#fff; }

.clearfix::after {
    content: "";
    clear: both;
    display: table;
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
  xhttp.open("GET", "http://192.168.235.173:8080/MessageService/webapi/profile/checkId?mailId="+str, true);
  xhttp.send();   
}

</script>


<body class="login">
<!-- header starts here -->
<div id="facebook-Bar">
  <div id="facebook-Frame">
    <div id="logo"> <a ><h1>GMAIL<h1></a> </div>
    <div id="header-main-right">
      <div id="header-main-right-nav">
        
			<form action="/Mail/Login.html" method="post" name="login_form" id="login_form">
          <table border="0" style="border:none">
            <tr>
              <td ><input type="text" tabindex="1" id="email" placeholder="Email" name="mailId" class="inputtext radius1" value="" required/></td>
              <td ><input type="password" tabindex="2"  id="pass" placeholder="Password" name="password"  class="inputtext radius1" required/></td>
              <td ><input type="submit" class="fbbutton" name="login" value="Login" /></td>
            </tr>
            <tr>
              <td><label>
                  <input id="persist_box" type="checkbox" name="persistent" >
                  <span style="color:#ccc;">Keep me login</span></label></td>
              <td>
              <span style="color:red; font-size: 12px;">Invalid mailId or Password</span></td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- header ends here -->
<div class="loginbox radius">
  <h2 style="color:#141823; text-align:center;">Welcome to GMAIL</h2>
  <div class="loginboxinner radius">
    <div class="loginheader">
      <h4 class="title">Connect with person and the world around you.</h4>
    </div>
    <!--loginheader-->
    <div class="loginform">
      <form  id="login" name="myform" action="http://192.168.235.173:8080/MessageService/webapi/profile/signup" method="post">
        <p>
          <input type="text" name="firstName" placeholder="First Name" requried/>
         
        </p>
        <p>
          <input type="text" name="lastName" placeholder="Last Name" requried/>
         
        </p>
       
       
        <p>
          <input type="password" name="password" placeholder="Password"  requried/>
        </p>
        <p>
          <input type="text" id="email" name="mailId" placeholder=" Email" onkeyup="showHint(this.value)" requried/>
        <div class="clearfix">
        <span id="msg"></span>
        </div>
        </p>
        
        <p>
          
        <input   type="submit" class="fbbutton" name="login" value="Signup" /> 
        </p>
<marquee>THIS IS GMAIL APPLICATION
		
</marquee>
&copy;Copyright 2016-2017. All rights reserved!
      </form>
    </div>
    <!--loginform-->
  </div>
  <!--loginboxinner-->
</div>
<!--loginbox-->
</body>
</html>