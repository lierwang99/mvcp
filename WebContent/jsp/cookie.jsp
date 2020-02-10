<%@ page language="java" session="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//新建一个cookie
		Cookie cookie = new Cookie("uuid", "uuidValue");
		response.addCookie(cookie);
	
	out.print("	接下来是显示cookie<br>");
		Cookie[] cookieArr = request.getCookies();
		if (cookieArr != null && cookieArr.length > 0) {
			for (int x=0;x<cookieArr.length;x++) {
				out.println(cookie);
			}

		}
	%>
</body>
</html>