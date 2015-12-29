<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int sum=0;

	//쿠키는 여러개가 있는데 한꺼번에 전송되므로, 찾아서 써야함
	Cookie[] cookies =  request.getCookies();

	for(Cookie c : cookies){
		if(c.getName().equals("sum"))
		{
			sum = Integer.parseInt(c.getValue());
			break;
		}
	}
	
	pageContext.setAttribute("sum", sum);
	
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		sum(session) : ${sessionScope.sum}
	</div>
	
	<div>
		sum(application) : ${applicationScope.sum}
	</div>
	
	<div>
		sum(cookie) : ${sum}
	</div>

</body>
</html>