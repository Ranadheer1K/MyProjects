package org.testpress.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.testpress.bean.QuestionBean;
import org.testpress.dao.QuestionsDAO;

public class QuestionInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
		HttpSession session = request.getSession();
		
		int questionNo;
		String question = "";
		String option1 = "";
		String option2 = "";
		String option3 = "";
		String option4 = "";
		String result = "";
	
		questionNo = Integer.parseInt(request.getParameter("num"));
		question = request.getParameter("question");
		option1 = request.getParameter("option1");
		option2 = request.getParameter("option2");
		option3 = request.getParameter("option3");
		option4 = request.getParameter("option4");
		
		System.out.println("Question :" + question);
		QuestionBean questionBean = new QuestionBean();
		
		if (questionNo <= 10) {
			questionBean.setQuestionNo(questionNo);
			questionBean.setQuestion1(question);
			questionBean.setOption1(option1);
			questionBean.setOption2(option2);
			questionBean.setOption3(option3);
			questionBean.setOption4(option4);
			
			QuestionsDAO questionsDAO = new QuestionsDAO();
			result = questionsDAO.insertQuestions(questionBean);
			System.out.println("Result : " + result);
			
			if (result.indexOf("0;") != -1) {
				questionList = questionsDAO.getAllQuestions();
				session.setAttribute("questionsList", questionList);
				request.setAttribute("msg", result.substring(2));
				rd = request.getRequestDispatcher("/successanddisplay.jsp");
			}
		} else {
			request.setAttribute("msg", "The question number must be less than or equal to 10");
			rd = request.getRequestDispatcher("/staffmain.jsp");
		}
		rd.forward(request, response);
	}
}
