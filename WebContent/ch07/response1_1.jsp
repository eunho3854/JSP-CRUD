<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	response.setHeader("Pragma", "no-cache");
	if(request.getProtocol().equals("HTTP/1.1")) {
		response.setHeader("Cache-Control", "no-store");
	}
%>
<h1>Response Example1</h1>
http://localhost:8000/hello/ch07/request1.jsp가 <p/>
http://localhost:8000/hello/ch07/request1_1.jsp로 변경이 되었습니다.
</body>
</html>