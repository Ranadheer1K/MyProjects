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

public class SubmitAnswerController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		QuestionsDAO questionsDAO = null;
		
		int qno;
		String answer = "";
		String result = "";

		qno = Integer.parseInt(request.getParameter("qno"));
		answer = request.getParameter("answer");
		
		questionsDAO = new QuestionsDAO();
		ArrayList<QuestionBean> questionList =  questionsDAO.getSingleQuestions(qno);
		result = questionsDAO.verifyAnswer(qno, answer);
		
		if(result.indexOf("0;") != -1) {
			request.setAttribute("questionList", questionList);
			request.setAttribute("msg", result.substring(2));
			rd = request.getRequestDispatcher("/test.jsp");
		} else {
			request.setAttribute("questionList", questionList);
			request.setAttribute("msg", result.substring(3));
			rd = request.getRequestDispatcher("/test.jsp");
		}
		rd.forward(request, response);
	}
}
