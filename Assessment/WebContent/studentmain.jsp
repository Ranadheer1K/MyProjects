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
  StudentBean studentBean = null;
%>
<%
  error_msg = (String) request.getAttribute("error_msg");
  studentBean = (StudentBean) session.getAttribute("studentBean");
%>

<%!
     String msg = "";
  %>
  
  <%
     try {
        msg = (String) request.getAttribute("msg");
     } catch(Exception e) {
    	 msg = "";
     }
  %>
  
<table style="margin-top:50px;">
<% 
  if(studentBean != null && !studentBean.equals("")) {
	  %>
	    <tr> 
          <td>Login Username: </td>
          <td><%=studentBean.getUserName() %></td>
          <td>&nbsp;</td>
          <td>Last login time:</td>
          <td><%=studentBean.getLastLoginTime() %></td>
          <td>&nbsp;</td>
          <td><a href="index.jsp">Logout</a></td>
       </tr>
      <%
  }
  %>
  </table>
  
  <form action="start" method="get">
    <input type="hidden" name="value" id="value" value="1">
    <input type="submit" value="START-TEST">
  </form>
  
  
</body>
</center>
</html>