<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br><br>
产品名称：<%
String name = (String)request.getAttribute("pname");
out.println(name);
%>
<br><br>
	<form action="<%=request.getContextPath()%>/addCar.pdo" method="post">
		<input type="hidden" name="pname" value="<%=name%>" /> 
		<input style="width: 120px; height: 30px; background: green; color: #fff;" type="submit" value="加入购物车" />
	</form>

</body>
</html>