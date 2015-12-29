<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action ="../Add" method="post">
			<ul>
					<li><input name="cnt"  />
					<li><label for="x">X:</label><input name="x" /></li>
					<li><label for="y">Y:</label><input name="y" /></li>
			</ul>
			<%for (int i=0; i<10; i++){ %>
			<p>result=<% out.print(3+4); %></p>
			<%} %>
			<p><input type="submit"  value="덧셈" /></p>
	</form>
</body>
</html>