<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<br>
		${empty requestScope.note }
	<c:if test="${not empty requestScope.note }">
		<span colspan="2" style="color: red; font-weight: bolder;">${note}</span>
	</c:if>
	<%
		String note = (String) request.getAttribute("note");
		if (note != null) {
	%>
	<span colspan="2" style="color: red; font-weight: bolder;"><%=note%></span>
	<%
		}
	%>
	

	<form action="<%=request.getContextPath()%>/login.udo" method="get">
		用户名:<input type="text" name="username" /> <br>
		<br> 密 &nbsp;&nbsp;码:<input type="text" name="password" />
		<br>
		<br> <input type="radio" name="expiredays" value="7">记住我一周<input
			type="radio" name="expiredays" value="30">记住我一个月<input
			type="radio" name="expiredays" value="100"> 永远记住我 <br>
		<br> <input type="submit" value="登录" />

	</form>


</body>
</html>