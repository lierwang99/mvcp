<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<%
 HttpSession sess = request.getSession();
sess.setAttribute("ccc","mySession");

out.write("取出session: "+session.getAttribute("ccc"));

%>

</body>
</html>