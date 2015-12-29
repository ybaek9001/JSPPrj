<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int x = 0;
	int y = 0;

	if (request.getMethod().equals("POST")) {
		String _x = request.getParameter("x");
		String _y = request.getParameter("y");
		if (_x != null || !_x.equals(""))
			x = Integer.parseInt(_x);
		if (_y != null || !_y.equals(""))
			y = Integer.parseInt(_y);
	}

	int sum = x + y;
	pageContext.setAttribute("x", x);
	pageContext.setAttribute("y", y);
	pageContext.setAttribute("sum", sum);

	session.setAttribute("sum", sum);
	application.setAttribute("sum", sum);	
	
	Cookie cookie = new Cookie("sum", String.valueOf(sum));    
    cookie.setMaxAge(60*60*24*30);
	response.addCookie(cookie);
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<div>
			<label>X:</label> <input type="text" name="x" value="${x}" />
		</div>
		<div>
			<label>Y:</label> <input type="text" name="y" value="${y}" //>
		</div>
		<div>
			<input type="submit" value="덧셈" /> <input type="submit"
				value="어플리케이션" /> <input type="submit" value="세션" /> <input
				type="submit" value="쿠키" />
		</div>
		<div>덧셈결과는 : ${sum}</div>

		<div>
			<a href="mypage.jsp">마이페이지</a>
		</div>
	</form>
</body>
</html>