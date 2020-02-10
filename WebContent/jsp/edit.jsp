
<%@page import="cn.liyan.mvcp.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
tr {
	height: 40px;
}

td {
	padding: 10px;
}
</style>
</head>
<body>
	<%
		User user = (User) request.getAttribute("user");
	%>
	<form action="<%=request.getContextPath()%>/updatedo.udo" method="post">
		<input type="hidden" name="id" value="<%=user.getId()%>" />
		<table
			style="margin-left: 100px; padding: 50px; border: 1px #ccc solid; width: 400px;">
			<c:if test="${not empty note }">
			<tr>
				<td colspan="2" style="text-align: right;color:red;font-weight:bolder;">${note }</td>
			</tr>
			</c:if>
			<tr>
				<td style="text-align: right;">姓名：</td>
				<td style=""><input type="text" name="username"
					value="${user.username}" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">密码：</td>
				<td style=""><input type="text" name="password"
					value="${user.password}" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">地址：</td>
				<td style=""><input type="text" name="address"
					value="${user.address }" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">电话：</td>
				<td style=""><input type="text" name="phoneNo"
					value="${user.phoneNo}" /></td>
			</tr>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="提交修改"></td>

			</tr>

		</table>
	</form>
</body>
</html>