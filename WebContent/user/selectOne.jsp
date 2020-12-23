<%@page import="com.cos.hello.controller.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../layout/header.jsp" %>

<h1>User Info</h1>
<%

	// sessionScope
	// String result = (String)session.getAttribute("result");

	// requestScope
	String result = (String)request.getAttribute("result");
	
	// 세션만 명시적으로 적어주고 리퀘스트는 안 적음.
%>

<%=result %>

<h1>${sessionScope.result}</h1>
<h1>${result}</h1>
</body>
</html>
