<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<form action="<%=request.getContextPath()%>/add.udo" method="post">
		<table
			style="margin-left: 100px; padding: 50px; border: 1px #ccc solid; width: 600px;">
			<%
			String note = (String)request.getAttribute("note");
			if(note != null && !"".equals(note) ){ 
			%>
			<tr>
				<td  colspan="2"  style="text-align: right;"><%=note %></td>
			</tr>
			<%} %>
			<tr>
				<td style="text-align: right;">姓名：</td>
				<td style=""><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">密码：</td>
				<td style=""><input type="text" name="password" /></td>
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
					type="submit" value="注册用户" /> </td>

			</tr>

		</table>
	</form>
</body>
</html>