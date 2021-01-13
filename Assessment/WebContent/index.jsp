<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<center>
<body style="margin-top:200px;">
<form action="login" method="get" name="form1" id="form1">
<table>
  <tr>
    <td colspan="2" align="center">LOGIN FORM</td>
  </tr>
  <tr>
    <td>Username <font color="red">*</font></td>
    <td><input type="text" name="uName" id="uName"></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
     <td>Password <font color="red">*</font></td>
    <td><input type="password" name="uPass" id="uPass"></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" align="right"><input type="button" value="Login" onclick="validUserDetails()"></td>
  </tr>
</table>
</form>

<script type="text/javascript">
   function validUserDetails() {
	   var username = "";
	   var password = "";
	   username = document.getElementById("uName").value;
	   password = document.getElementById("uPass").value;
	   
	   if(username == "") {
		   alert("Please enter the username");
		   document.getElementById("uName").focus();
		   return false;
	   }
	   
	   if(password == "") {
		   alert("Please enter the password");
		   document.getElementById("uPass").focus();
		   return false;
	   }
	   
	   if(username.length < 5) {
		   alert("Username should be atleast 5 characters");
		   document.getElementById("uName").focus();
		   return false;
	   }
	   
	   if(password.length < 8) {
		   alert("Password should be atleast 8 characters");
		   document.getElementById("uPass").focus();
		   return false;
	   }
	   document.getElementById("form1").submit();
   }
</script>
</body>
</center>
</html>