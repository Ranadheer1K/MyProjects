package org.testpress.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testpress.bean.QuestionBean;
import org.testpress.dao.QuestionsDAO;

public class GetQuestionController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		int questionNo;
		questionNo = Integer.parseInt(request.getParameter("value"));
		System.out.println("Question number in Get Question Controller: " + questionNo);
		
		QuestionsDAO questionsDAO = new QuestionsDAO();
		ArrayList<QuestionBean> questionList = questionsDAO.getSingleQuestions(questionNo);
		if(questionList != null && !questionList.isEmpty()) {
			request.setAttribute("questionList", questionList);
			rd = request.getRequestDispatcher("/test.jsp");
		} else {
			request.setAttribute("msg", "Invalid question number recieved");
			rd = request.getRequestDispatcher("/studentmain.jsp");
		}
		rd.forward(request, response);
	}
}
