<%@ page language="java" contentType="text/html; charset=UTF-8"
	buffer = "5kb"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Out Example1</title>
</head>
<body>
<%
	int totalBuffer = out.getBufferSize();
	int remainBuffer = out.getRemaining();
	int useBuffer = totalBuffer - remainBuffer;
%>
<h1>Out Example1</h1>
<b>현재 페이지의 Buffer 상태</b><p/>
출력 Buffer의 전체 크기 <%=totalBuffer %>byte<p/>
남은 Buffer의 크기 <%=remainBuffer %>byte<p/>
현재 Buffer의 사용량 <%=useBuffer %>byte<p/>
</body>
</html>