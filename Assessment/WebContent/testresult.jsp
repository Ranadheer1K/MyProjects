<%@page import="org.testpress.bean.ResultBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Result</title>
</head>
<center>
<body>
  <%!
      ArrayList<ResultBean> resultList = null;
      ResultBean resultBean = null;
      String message = "";
      Integer totalScore = null;
  %>
  <%
     try {
    	 resultList = (ArrayList<ResultBean>) request.getAttribute("resultList");
     } catch(Exception e) {
    	 resultList = null;
     }
  
  try {
 	 message = (String) request.getAttribute("message");
  } catch(Exception e) {
	  message = "";
  }
  
  try {
	  totalScore = (int) request.getAttribute("totalScore");
	  } catch(Exception e) {
		  totalScore = null;
	  }
  
  %>
  
  <table border="1" style="border-collapse:collapse; margin-top:70px;">
    <tr><td>Question No</td> <td>Question Name</td> <td>Option1</td> <td>Option2</td> <td>Option3</td> <td>Option4</td> <td>Your Answer</td> <td>Validation</td> <td>Score</td></tr>
    <% 
     if(resultList != null && !resultList.equals("") && !resultList.isEmpty()) {
   	  for(int i = 0; i < resultList.size(); i++) {
   		  resultBean = resultList.get(i);
   		  %>
   		   <tr>
   		      <td><%=resultBean.getQuestionNo() %></td>
   		      <td><%=resultBean.getQuestionName() %></td>
   		      <td><%=resultBean.getOption1() %></td>
   		      <td><%=resultBean.getOption2() %></td>
   		      <td><%=resultBean.getOption3() %></td>
   		      <td><%=resultBean.getOption4() %></td>
   		      <td><%=resultBean.getAnswer() %></td>
   		      <td><%=resultBean.getValid() %></td>
   		      <td><%=resultBean.getScore() %></td>
   		   </tr>
   	<%
   	  }
     } else {
    	 %>
    	 <tr>
    	    <td><%=resultList%></td>
    	 </tr>
    	 <%
     }
    %> 
  </table>
  
  <%
      if(totalScore != null) {
    	  %>
    	  Your Total Score: <font color="green"><%=totalScore %></font>
    	  <%
    	  if(totalScore > 5) {
    		  %>
    		  Status: <font color="green">Pass</font>
    		  <%
    	  } else {
    		  %>
    		  Status: <font color="red">Fail</font>
    		  <%
    	  }
      }
    %>
    <a href="index.jsp">Logout</a>
  
</body>
</center>
</html>