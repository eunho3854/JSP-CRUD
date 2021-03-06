package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.hello.config.DBConn;
import com.cos.hello.dto.JoinDto;
import com.cos.hello.dto.LoginDto;
import com.cos.hello.model.Users;

public class UsersDao {

	public Users selectById(int id) {
		
		StringBuffer sb = new StringBuffer(); // String전용 컬렉션(동기화)
		sb.append("SELECT id, password, username, email FROM users ");
		sb.append("WHERE id = ?");
		String sql = sb.toString(); // String으로 만듦.
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.email(rs.getString("email"))
						.build();
				return userEntity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Users login(LoginDto loginDto) {

		// 2번 DB에 연결해서 3가지 값을 INSERT 하기
		StringBuffer sb = new StringBuffer(); // String전용 컬렉션(동기화)
		sb.append("SELECT id, username, email FROM users ");
		sb.append("WHERE username = ? AND password = ?");
		String sql = sb.toString(); // String으로 만듦.
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginDto.getUsername());
			pstmt.setString(2, loginDto.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.build();
				return userEntity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int insert(JoinDto joinDto) {

		// 2번 DB에 연결해서 3가지 값을 INSERT 하기
		StringBuffer sb = new StringBuffer(); // String전용 컬렉션(동기화)
		sb.append("INSERT INTO users(username, password, email) ");
		sb.append("VALUES(?,?,?)");
		String sql = sb.toString(); // String으로 만듦.
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinDto.getUsername());
			pstmt.setString(2, joinDto.getPassword());
			pstmt.setString(3, joinDto.getEmail());
			// 변경된 행의 개수를 리턴
			int result = pstmt.executeUpdate(); // DML 문장은 다 executeUpdate
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
	
	public int update(Users user) {

		// 2번 DB에 연결해서 3가지 값을 INSERT 하기
		StringBuffer sb = new StringBuffer(); // String전용 컬렉션(동기화)
		sb.append("UPDATE users SET password = ?, email = ? ");
		sb.append("WHERE id = ?");
		String sql = sb.toString(); // String으로 만듦.
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setInt(3, user.getId());
			// 변경된 행의 개수를 리턴
			int result = pstmt.executeUpdate(); // DML 문장은 다 executeUpdate
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
	
	public int delete(int id) {

		// 2번 DB에 연결해서 3가지 값을 INSERT 하기
		StringBuffer sb = new StringBuffer(); // String전용 컬렉션(동기화)
		sb.append("DELETE FROM users ");
		sb.append("WHERE id = ?");
		String sql = sb.toString(); // String으로 만듦.
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			// 변경된 행의 개수를 리턴
			int result = pstmt.executeUpdate(); // DML 문장은 다 executeUpdate
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
}
