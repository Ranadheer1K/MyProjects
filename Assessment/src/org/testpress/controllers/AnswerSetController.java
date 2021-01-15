package org.testpress.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testpress.dao.QuestionsDAO;

public class AnswerSetController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionsDAO questionsDAO = null;
		RequestDispatcher rd = null;
		
		int questionNo;
		String answer = "";
		String result = "";
		
		questionNo = Integer.parseInt(request.getParameter("qNo"));
		answer = request.getParameter("answer");
		questionsDAO = new QuestionsDAO();
		result = questionsDAO.insertAnswer(questionNo, answer);
		
		if(result.indexOf("0;") != -1) {
			request.setAttribute("msg", result.substring(2));
			rd = request.getRequestDispatcher("/successanddisplay.jsp");
		} else {
			request.setAttribute("msg", result.substring(3));
			rd = request.getRequestDispatcher("/successanddisplay.jsp");
		}
		rd.forward(request, response);
	}
}
