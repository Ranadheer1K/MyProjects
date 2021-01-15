package org.testpress.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.testpress.bean.QuestionBean;
import org.testpress.bean.ResultBean;
import org.testpress.service.Utility;

public class QuestionsDAO {
	
	public String insertQuestions(QuestionBean questionBean) {
		System.out.println(questionBean.getQuestion1());
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "";
		try {
			con = Utility.getConnection();
		    String sql = "insert into question_set values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, questionBean.getQuestionNo());
			pstmt.setString(2, questionBean.getQuestion1());
			pstmt.setString(3, questionBean.getOption1());
			pstmt.setString(4, questionBean.getOption2());
			pstmt.setString(5, questionBean.getOption3());
			pstmt.setString(6, questionBean.getOption4());
			
			int no = pstmt.executeUpdate();
			if(no > 0) {
				result = "0; Question inserted successfully";
			} else {
				result = "-1; Question insertion failed";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	    return result;
	}
	
	
	
	
	public ArrayList<QuestionBean> getAllQuestions() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		ArrayList<QuestionBean> questionList = null;
		try {
			con = Utility.getConnection();
		    String sql = "select * from question_set";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				questionList = new ArrayList<QuestionBean>();
				do {
					QuestionBean questionBean = new QuestionBean();
					questionBean.setQuestionNo(rs.getInt(1));
					questionBean.setQuestion1(rs.getString(2));
					questionBean.setOption1(rs.getString(3));
					questionBean.setOption2(rs.getString(4));
					questionBean.setOption3(rs.getString(5));
					questionBean.setOption4(rs.getString(6));
					questionList.add(questionBean);
				} while (rs.next());
			}
			con.commit();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	    return questionList;
	}
	
	
	
	/*******************Get a Single question****************/
	public ArrayList<QuestionBean> getSingleQuestions(int questionNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QuestionBean> questionList = null;
		
		try {
			con = Utility.getConnection();
		    String sql = "select * from question_set where sno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, questionNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				questionList = new ArrayList<QuestionBean>();
				do {
					QuestionBean questionBean = new QuestionBean();
					questionBean.setQuestionNo(rs.getInt(1));
					questionBean.setQuestion1(rs.getString(2));
					questionBean.setOption1(rs.getString(3));
					questionBean.setOption2(rs.getString(4));
					questionBean.setOption3(rs.getString(5));
					questionBean.setOption4(rs.getString(6));
					questionList.add(questionBean);
					sql = "insert into result(question_no, question_name, option1, option2, option3, option4) values(?, ?, ?, ?, ?, ?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, rs.getInt(1));
					pstmt.setString(2, rs.getString(2));
					pstmt.setString(3, rs.getString(3));
					pstmt.setString(4, rs.getString(4));
					pstmt.setString(5, rs.getString(5));
					pstmt.setString(6, rs.getString(6));
					pstmt.executeUpdate();
				} while (rs.next());
			}
			con.commit();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	    return questionList;
	}
	
	
	
	/*******************Answer Verification****************/
	public String verifyAnswer(int qno, String answer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		String sql = "";
		
		try {
			con = Utility.getConnection();
		    sql = "select * from answers where qno=? and answer=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qno);
			pstmt.setString(2, answer);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = "0; Correct Answer";
				sql = "update result set answer=?, valid=?, score=? where question_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, answer);
				pstmt.setString(2, "Correct");
				pstmt.setInt(3, 1);
				pstmt.setInt(4, qno);
				pstmt.executeUpdate();
			} else {
				result = "-1; Wrong Answer";
				sql = "update result set answer=?, valid=?, score=? where question_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, answer);
				pstmt.setString(2, "Wrong");
				pstmt.setInt(3, 0);
				pstmt.setInt(4, qno);
				pstmt.executeUpdate();
			}
			con.commit();
					
		} catch (SQLException e) {
			result = "-1; Wrong Answer";
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	    return result;
	}
	
	/*******************Answers Count****************/
	public Integer countAnswer() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int total_score = 0;
		try {
			con = Utility.getConnection();
		    stmt = con.createStatement();
			String sql = "select sum(score) from result";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				total_score = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	    return total_score;
	}
	

	/************************GET TEST RESULT*************************/
	public ArrayList<ResultBean> getTestResult() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		ArrayList<ResultBean> resultList = null;
		try {
			con = Utility.getConnection();
		    String sql = "select * from result";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				resultList = new ArrayList<ResultBean>();
				do {
					ResultBean resultBean = new ResultBean();
					resultBean.setQuestionNo(rs.getInt(1));
					resultBean.setQuestionName(rs.getString(2));
					resultBean.setOption1(rs.getString(3));
					resultBean.setOption2(rs.getString(4));
					resultBean.setOption3(rs.getString(5));
					resultBean.setOption4(rs.getString(6));
					resultBean.setAnswer(rs.getString(7));
					resultBean.setValid(rs.getString(8));
					resultBean.setScore(rs.getInt(9));
					resultList.add(resultBean);
				} while (rs.next());
			}
			con.commit();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	    return resultList;
	}
	
	/***********************Answer Insertion***********/
	public String insertAnswer(int qNo, String answer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "";
		
		try {
			con = Utility.getConnection();
		    String sql = "insert into answers values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			pstmt.setString(2, answer);
			int no = pstmt.executeUpdate();
			if(no > 0) {
				result = "0; Answer inserted successfully";
			} else {
				result = "-1; Answer insertion failed";
			}
			System.out.println("Result: " + result);
		    con.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
		}
		return result;
	}
	
	
	
}
