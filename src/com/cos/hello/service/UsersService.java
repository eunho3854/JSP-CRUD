package com.cos.hello.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.config.AttackFilter;
import com.cos.hello.dao.UsersDao;
import com.cos.hello.dto.JoinDto;
import com.cos.hello.dto.LoginDto;
import com.cos.hello.model.Users;
import com.cos.hello.util.Script;

public class UsersService {

	
	public void 회원가입(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JoinDto loginDto = (JoinDto) req.getAttribute("dto");

		UsersDao usersDao = new UsersDao(); // 싱글턴 패턴으로 바꾸기
		int result = usersDao.insert(loginDto);
		
		if (result == 1) {
			// 3번 INSERT가 정상적으로 되었다면 login.jsp를 응답 !
			resp.sendRedirect("auth/login.jsp");
		} else {
			resp.sendRedirect("auth/join.jsp");
		}
	}

	
	public void 로그인(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		LoginDto loginDto = (LoginDto) req.getAttribute("dto");

		UsersDao usersDao = new UsersDao();
		Users userEntity = usersDao.login(loginDto);

		if (userEntity != null) {
			HttpSession session = req.getSession();
			session.setAttribute("sessionUser", userEntity);
			
			// 한글처리를 위해 resp 객체를 건드린다.
			// MIME 타입
			// http Header에 Content-Type
			Script.href(resp, "index.jsp", "로그인 성공");
		} else {
			Script.back(resp, "로그인 실패");
		}

	}

	public void 유저정보보기(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		HttpSession session = req.getSession();

		Users user = (Users) session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();

		if (user != null) {
			Users userEntity = usersDao.selectById(user.getId());
			req.setAttribute("user", userEntity);
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
		} else {
			resp.sendRedirect("auth/login.jsp");
		}
	}

	
	public void 유저정보수정페이지(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		HttpSession session = req.getSession();

		Users user = (Users) session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();

		if (user != null) {
			Users userEntity = usersDao.selectById(user.getId());
			req.setAttribute("user", userEntity);
			RequestDispatcher dis = req.getRequestDispatcher("user/updateOne.jsp");
			dis.forward(req, resp);
		} else {
			resp.sendRedirect("auth/login.jsp");
		}
	}
	
	public void 회원수정(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		Users user = Users.builder()
				.id(id)
				.password(password)
				.email(email)
				.build();

		UsersDao usersDao = new UsersDao(); // 싱글턴 패턴으로 바꾸기
		int result = usersDao.update(user);

		if (result == 1) {
			
			// 데이터를 가져가려면 controller를 거쳐야함.
			resp.sendRedirect("index.jsp");
		} else {
			resp.sendRedirect("user?gubun=updateOne");
		}
	}
	
	public void 회원삭제(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		UsersDao usersDao = new UsersDao(); // 싱글턴 패턴으로 바꾸기
		int result = usersDao.delete(id);

		if (result == 1) {
			HttpSession session = req.getSession();
			session.invalidate(); // 세션 무효화
			resp.sendRedirect("index.jsp");
		} else {
			resp.sendRedirect("user?gubun=selectOne");
		}
	}
}
