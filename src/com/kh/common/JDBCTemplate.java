package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	// 1. Connection객체 생성 후 Connection을 반환하는 메소드
	public static Connection getConnection() {
		// Properties
		Properties prop = new Properties();
		
		// 읽어들이고자하는 driver.properties파일의 물리적인 경로 제시
		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
		//C:\Web-worspace2\JSP_Project\WebContent\WEB-INF\classes\sql\driver\driver.properties
		
		try {
			prop.load(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		
		
		try {
			//1) jdbc driver등록
			Class.forName(prop.getProperty("driver"));
			
			//2) db와 적속된 Connection 객체 생성
			conn = DriverManager.getConnection(prop.getProperty("url"),
												prop.getProperty("username"),
												prop.getProperty("password"));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//2. 전달받은 JDBC용 객체를 반납시켜주는 메소드(객체별로 오버로딩)
	//2_1) Connection객체를 전달받아서 반납시켜주는 메소드
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//2_2) Statement객체를 전달받아서 반납시켜주는 메소드
	public static void close(Statement stmt) { // Statement + PreparedStatment 둘다
		// 매개변수로 전달 가능(다형성)
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//2_3) ResultSet 객체를 전달받아서 반납시켜주는 메소드(오버로딩)
	public static void close(ResultSet rset) {
		try {
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. 전달받은 Connection객체를 가지고 트랜잭션 처리를 해주는 메소드
	// 3_1.) Commit메소드
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3_2) rollback메소드
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
