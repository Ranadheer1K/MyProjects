<%@page import="org.testpress.bean.QuestionBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>main</title>
</head>
<center>
<body>
  <%!
     String msg = "";
     ArrayList<QuestionBean> questionList = null;
  %>
  <%
     try {
        msg = (String) request.getAttribute("msg");
     } catch(Exception e) {
    	 msg = "";
     }
     questionList = (ArrayList<QuestionBean>) session.getAttribute("questionsList");
  %>
  
  <table border="1" style="border-collapse:collapse; margin-top:50px;">
     <tr><td>Question No</td> <td>Question</td> <td colspan="4" align="center">Options</td></tr>
  <%
    if(questionList != null && !questionList.equals("")) {
    	for(int i = 0; i < questionList.size(); i++) {
    		QuestionBean qBean = questionList.get(i);
    		%>
    		<tr>
    		<td><%=qBean.getQuestionNo() %></td>
    		<td><%=qBean.getQuestion1() %></td>
    		<td><%=qBean.getOption1() %></td>
    		<td><%=qBean.getOption2() %></td>
    		<td><%=qBean.getOption3() %></td>
    		<td><%=qBean.getOption4() %></td>
    		</tr>
    		<%
    	} 	
      } else {
    		%>
    		<tr><td>No record found</td></tr>
    		<%	
    }
  %>
  </table>
     
     <% 
        if(msg != null && !msg.equals("")) {
           %>
              <font color="green"><%=msg %></font> <br>
          <% 
        }
     %>

<form action="insertanswer" method="get" name="form1" id="form1">
<table>
   <tr>
      <td COLSPAN="2" align="center">SET ANSWER</td>
   </tr>
   <tr>
      <td>Question No</td>
      <td><input type="text" name="qNo" id="qNo"></td>
   </tr>
   <tr>
      <td>Answer</td>
      <td><input type="text" name="answer" id="answer"></td>
   </tr>
   <tr>
     <td colspan="2" align="right"><input type="button" value="add" onclick="validation()"></td>
   </tr>
</table>
</form>

<script type="text/javascript">
    function validation() {
	   var qno = "";
	   var answer = "";
	   qno = document.getElementById("qNo").value;
	   answer = document.getElementById("answer").value;
	   if(qno == "") {
		   alert("Please enter the question no");
		   document.getElementById("qNo").focus();
		   return false;
	   }
	   
	   if(answer == "" || answer == "a" || answer == "b" || answer == "c" || answer == "d") {
		   alert("Please enter the answer as A,B,C,D only");
		   document.getElementById("answer").focus();
		   return false;
	   }
	   document.getElementById("form1").submit();
   }

</script>
  
 <a href="staffmain.jsp">Insert another question</a>
</body>
</center>
</html>