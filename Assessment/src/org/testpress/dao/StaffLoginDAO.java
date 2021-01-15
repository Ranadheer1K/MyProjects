package org.testpress.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testpress.bean.StaffBean;
import org.testpress.service.Utility;

public class StaffLoginDAO {
	
	public String validUsernameorPassword(StaffBean staffBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = null;
		
		try {
			con = Utility.getConnection();
			pstmt = con.prepareStatement("select * from staff where username=? and password=?");
			pstmt.setString(1, staffBean.getUserName());
			pstmt.setString(2, staffBean.getPassWord());
			int no = pstmt.executeUpdate();
			
			if(no > 0) {
				result = "staff login successfully";
			} else {
				result = "Login failed";
			}
			System.out.println(" inside login dao : " + result );
			
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
		return result;
	}
	
	/*****************updateLastLoginDate***************/
	
	public void updateLastLoginDate(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = null;
		
		try {
			con = Utility.getConnection();
			pstmt = con.prepareStatement("update staff set last_login_time = sysdate where username=?");
			pstmt.setString(1, username);
			int no = pstmt.executeUpdate();
			con.commit();
			System.out.println(no + " time last login date updated");
			
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
	}
	
	
/*****************getLastLoginTime***************/
	
	public String getLastLoginTime(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String lastLoginTime = "";
		ResultSet rs = null;
		
		try {
			con = Utility.getConnection();
			String sql = "select TO_CHAR(last_login_time, 'DD/MM/YYYY HH24:MI:SS') from staff where username=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				do {
					lastLoginTime = rs.getString(1);
					if(lastLoginTime == null) {
						lastLoginTime = "";
					}
				} while(rs.next());	
			} else {
				lastLoginTime = "";
			}
			con.commit();
			System.out.println("get last login time : " + lastLoginTime);
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
		return lastLoginTime;
	}

}
