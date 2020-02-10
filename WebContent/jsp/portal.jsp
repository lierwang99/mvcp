<%@page import="java.util.Enumeration"%>
<%@page import="cn.liyan.mvcp.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		String usert = (String) session.getAttribute("user");
		out.println(usert);
	%>


	<%-- 	<c:if test="${empty sessionScope.user }"> --%>
	<%-- 		<c:redirect url="/index.jsp" /> --%>
	<%-- 	</c:if> --%>
	<form action="<%=request.getContextPath()%>/query.udo" method="post">
		<table
			style="margin-left: 100px; padding: 50px; border: 1px #ccc solid; width: 400px;">
			<tr>
				<td style="text-align: right;">姓名：</td>
				<td style=""><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">地址：</td>
				<td style=""><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">电话：</td>
				<td style=""><input type="text" name="phoneNo" /></td>
			</tr>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="查询用户" /></td>

			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><a
					href="<%=request.getContextPath()%>/jsp/add.jsp">注册用户</a></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center; background: gray;"><a
					href="<%=request.getContextPath()%>/logout.udo">注销用户</a></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center; background: yellow;"><a
					href="${pageContext.request.contextPath }/jsp/productList.jsp">shopping</a></td>
			</tr>



		</table>

	</form>

	<table style="margin-left: 100px; padding: 50px;" border="1"
		cellpadding="0" cellspacing="0">
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>密码</td>
			<td>电话</td>
			<td>地址</td>
			<td>注册日期</td>
			<td>操 作</td>
		</tr>

<%-- 				<% --%>
<!-- 		// List -->
<!-- 		<User> list = (List<User>) -->
<!-- 		request.getAttribute("userList"); // //out.println(list); // if (list -->
<!-- 		!= null && list.size() > 0) { // for (User user : list) { 		%> -->

		<!-- 		<tr> --> <%-- 		<td><%=user.getId()%></td> --%> <%-- 		<td><%=user.getUsername()%></td> --%>
		<%-- 		<td><%=user.getPassword()%></td> --%> <%-- 		<td><%=user.getPhoneNo()%></td> --%>
		<%-- 		<td><%=user.getAddress()%></td> --%> <%-- 		<td><%=user.getRegDate()%></td> --%>
		<!-- 		<td><a --> <%-- 			href="<%=request.getContextPath()%>/update.udo?id=<%=user.getId()%>">修改</a> --%>
		<!-- 			| <a --> <%-- 			href="<%=request.getContextPath()%>/delete.udo?id=<%=user.getId()%>">删除</a></a> --%>
<%-- 		<!-- 		</td> --> <!-- 		</tr> --> 			<% // } // } 			%> --%>
		<c:if test="${not empty userList }">
		<c:forEach items="${userList}" var="u">
			<tr>
				<td>${u.id }</td>
				<td>${u.username }</td>
				<td>${u.password }</td>
				<td>${u.phoneNo }</td>
				<td>${u.address }</td>
				<td>${ u.regDate}</td>
				<td><a
					href="${pageContext.request.contextPath }/update.udo?id=${u.id }">修改</a>
					| <a
					href="${pageContext.request.contextPath }/delete.udo?id=${u.id }">删除</a></a>
				</td>
			</tr>
			
		</c:forEach>
		
		</c:if>
		
	</table>
	<div>共${userList.size()}条数据</div>
	

	页面session:
	<%
		if (session != null) {
			System.out.println("门户sessionID:" + session.getId());
			out.println(session.getAttribute("user"));
			Enumeration<String> attributeNames = session.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
				System.out.println("------");
				System.out.println("session:" + attributeNames.nextElement());
			}
		}
	%>
</body>
</html>