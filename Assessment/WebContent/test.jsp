<%@page import="org.testpress.bean.QuestionBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>
</head>
<body>
   <%!
     ArrayList<QuestionBean> questionList = null;
     QuestionBean qBean = null;
     String msg = "";
     Integer correctAnswerCount = null;
  %>
  <%
  
  try {
	  questionList = (ArrayList<QuestionBean>) request.getAttribute("questionList");
  } catch(Exception e) {
	  questionList = null;
  }
  
  try {
	  msg = (String) request.getAttribute("msg");
  } catch(Exception e) {
	  msg = "";
  }
  
  try {
	  correctAnswerCount = (int) request.getAttribute("answerCount");
  } catch(Exception e) {
	  correctAnswerCount = null;
  }
  
    
  %>
  
 <center>
  <table border="0" style="border-collapse:collapse; margin-top:50px;">
  <%
    if(questionList != null && !questionList.equals("")) {
    	for(int i = 0; i < questionList.size(); i++) {
    		qBean = questionList.get(i);
    		%>
    		<tr>
    		  <td><%=qBean.getQuestionNo() %>.</td>
    		  <td><%=qBean.getQuestion1() %></td>
    		</tr>
    		<tr>
    		  <td colspan="2">A.<%=qBean.getOption1() %>&nbsp;</td>
    		</tr>
    		<tr>
    		  <td colspan="2">B.<%=qBean.getOption2() %>&nbsp;</td>
    		 </tr>
    		 <tr>
    		  <td colspan="2">C.<%=qBean.getOption3() %>&nbsp;</td>
    		 </tr>
    		 <tr>
    		  <td colspan="2">D.<%=qBean.getOption4() %>&nbsp;</td>
    		</tr>
    		<%
    	}
      }
  %>
  </table>

  
  <form action="answer" name="form1" id="form1">
     <input type="hidden" name="qno" id="qno" value=<%=qBean.getQuestionNo() %>>
     <input type="text" name="answer" id="answer">
     <input type="button" value="submit" onclick="validateChoice()"> 
     
     <script type="text/javascript">
         function validateChoice() {
        	 var answer = "";
        	 answer = document.getElementById("answer").value;
        	 if(answer == null || answer =="") {
        		 alert("Choice can't be empty");
        		 document.getElementById("answer").focus();
        		 return false;
        	 }
        	 document.getElementById("form1").submit();
         }
     </script>
  </form>
  
  <%
    if(msg != null && !msg.equals("")) {
    	%>
    		<font color="green"><%=msg %></font>
        <%
        if(qBean.getQuestionNo() == 10) {
        	%>
        	<form action="result" method="get">
    	       <input type="submit" value="VIEW-RESULT">
    	    </form>
        	<%
        }
    } 
  %>
  
  <form action="start">
     <input type="hidden" name="value" id="value" value=<%=qBean.getQuestionNo()+1%>> <br>
     <input type="submit" value="next">
  </form>
 
  
 </center>   
</body>
</html>