package org.testpress.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.testpress.bean.StudentBean;
import org.testpress.dao.StudentLoginDAO;
import org.testpress.bean.StaffBean;
import org.testpress.dao.StaffLoginDAO;

public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = null;
		
		String userName = null;
		String passWord = null;
		String staffResult = "";
		String studentResult = "";
		
		userName = request.getParameter("uName");
		passWord = request.getParameter("uPass");
		session = request.getSession();
		if(userName == null || userName.equals("")) {
			request.setAttribute("error_msg", "Username can not be blank");
			rd = request.getRequestDispatcher("/index.jsp");
		}
		
		if(passWord == null || passWord.equals("")) {
			request.setAttribute("error_msg", "Password can not be blank");
			rd = request.getRequestDispatcher("/index.jsp");
		}
		
		StaffBean staffBean = new StaffBean();
		staffBean.setUserName(userName);
		staffBean.setPassWord(passWord);
		StaffLoginDAO staffLoginDAO = new StaffLoginDAO();
		staffResult = staffLoginDAO.validUsernameorPassword(staffBean);
		System.out.println("Inside login controller: " + staffResult);
		
		StudentBean studentBean = new StudentBean();
		studentBean.setUserName(userName);
		studentBean.setPassWord(passWord);
		StudentLoginDAO studentLoginDAO = new StudentLoginDAO();
		studentResult = studentLoginDAO.validUsernameorPassword(studentBean);
		System.out.println(" inside logincontroller : " + studentResult );
		
		if(staffResult.contains("staff")) {
			System.out.println("STAFF");
			String lastLoginTime = staffLoginDAO.getLastLoginTime(userName);
			staffBean.setLastLoginTime(lastLoginTime);
			staffLoginDAO.updateLastLoginDate(userName);
			session.setAttribute("staffBean", staffBean);
			request.setAttribute("error_msg", staffResult.substring(2));
			rd = request.getRequestDispatcher("/staffmain.jsp");
		} else if(studentResult.contains("student")) {
			System.out.println("STUDENT");
			String lastLoginTime = studentLoginDAO.getLastLoginTime(userName);
			studentBean.setLastLoginTime(lastLoginTime);
			studentLoginDAO.updateLastLoginDate(userName);
			session.setAttribute("studentBean", studentBean);
			request.setAttribute("error_msg", studentResult.substring(2));
			rd = request.getRequestDispatcher("/studentmain.jsp");
		} else {
			request.setAttribute("error_msg", "Invalid username or password");
			rd = request.getRequestDispatcher("/index.jsp");
		}
		
		rd.forward(request, response);
		
	}
}
