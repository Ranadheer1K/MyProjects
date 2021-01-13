<%@page import="org.testpress.bean.StudentBean"%>
<%@page import="org.testpress.bean.StaffBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<center>
<body>
<%!
  String userName = ""; 
  String error_msg = "";
  StaffBean staffBean = null;
  String msg = "";
%>
<%
  error_msg = (String) request.getAttribute("error_msg");
  staffBean = (StaffBean) session.getAttribute("staffBean");
%>

  <%
     try {
        msg = (String) request.getAttribute("msg");
     } catch(Exception e) {
    	 msg = "";
     }
  %>

		<table>
			<tr>
				<td>Login Username:</td>
				<td><%=staffBean.getUserName()%></td>
				<td>&nbsp;</td>
				<td>Last login time:</td>
				<td><%=staffBean.getLastLoginTime()%></td>
				<td>&nbsp;</td>
				<td><a href="index.jsp">Logout</a></td>
			</tr>
       </table>

		<form action="insert" method="get" name="form1" id="form1">
			<table style="margin-top:70px;">
				<tr>
					<td colspan="2" align="center"><b>QUESTION SETUP</b></td>
				</tr>
				<tr>
					<td>Question no:</td>
					<td><input type="text" name="num" id="num"></td>
				</tr>
				<tr>
					<td>Question:</td>
					<td><input type="text" name="question" id="question"></td>
				</tr>
				<tr>
					<td>Option1</td>
					<td><input type="text" name="option1" id="option1"></td>
				</tr>
				<tr>
					<td>Option2</td>
					<td><input type="text" name="option2" id="option2"></td>
				</tr>
				<tr>
					<td>Option3</td>
					<td><input type="text" name="option3" id="option3"></td>
				</tr>
				<tr>
					<td>Option4</td>
					<td><input type="text" name="option4" id="option4"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="button" value="add" onclick="validateQuestions()">
				</tr>
			</table>

		<%
          if(msg != null && !msg.equals("")) {
        	  %>
			<font color="red"><%=msg %></font>
			<%
          }
       %>
		</form>

		<script type="text/javascript">
			function validateQuestions() {
				var questionNo = "";
				var question = "";
				var option1 = "";
				var option2 = "";
				var option3 = "";
				var option4 = "";

				questionNo = document.getElementById("num").value;
				questionName = document.getElementById("question").value;
				optionOne = document.getElementById("option1").value;
				optionTwo = document.getElementById("option2").value;
				optionThree = document.getElementById("option3").value;
				optionFour = document.getElementById("option4").value;

				if (questionNo == "") {
					alert("question number can't be empty");
					document.getElementById("num").focus();
					return false;
				}
				if (questionName == "") {
					alert("question can't be empty");
					document.getElementById("question").focus();
					return false;
				}
				if (optionOne == "") {
					alert("option1 can't be empty");
					document.getElementById("option1").focus();
					return false;
				}
				if (optionTwo == "") {
					alert("option2 can't be empty");
					document.getElementById("option2").focus();
					return false;
				}
				if (optionThree == "") {
					alert("option3 number can't be empty");
					document.getElementById("option3").focus();
					return false;
				}
				if (optionFour == "") {
					alert("option4 number can't be empty");
					document.getElementById("option4").focus();
					return false;
				}
				document.getElementById("form1").submit();
			}
		</script>

	</body>
</center>
</html>