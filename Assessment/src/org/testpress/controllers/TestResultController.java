package org.testpress.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testpress.bean.ResultBean;
import org.testpress.dao.QuestionsDAO;

public class TestResultController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionsDAO questionsDAO = null;
		ResultBean resultBean = null;
		
		RequestDispatcher rd = null;
		
		questionsDAO = new QuestionsDAO();
		resultBean = new ResultBean();
		ArrayList<ResultBean> resultList = questionsDAO.getTestResult();
		int score = questionsDAO.countAnswer();
		if(resultList != null && !resultList.isEmpty() && !resultList.equals("")) {
			request.setAttribute("resultList", resultList);
			request.setAttribute("totalScore", score);
			rd = request.getRequestDispatcher("/testresult.jsp");
		} else {
			request.setAttribute("resultList", "Test result is not available");
			rd = request.getRequestDispatcher("/testresult.jsp");
		}
		rd.forward(request, response);
	}
}
