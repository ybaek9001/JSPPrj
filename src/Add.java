import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Add")
public class Add extends HttpServlet {       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int sum = 0;
		
		if (request.getMethod().equals("POST")) {
			String _x = request.getParameter("x");
			String _y = request.getParameter("y");

			sum = Integer.parseInt(_x) + Integer.parseInt(_y);
		}
		
		PrintWriter out = response.getWriter();
		out.printf("result:%d", sum);
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("</head>");
		out.write("<body>");
		out.write("<form action =\"Add\" method=\"post\">");
		out.write("	<ul>");
		out.write("		<li><input name=\"cnt\"  />");
		out.write("					<li><label for=\"x\">X:</label><input name=\"x\" /></li>");
		out.write("					<li><label for=\"y\">Y:</label><input name=\"y\" /></li>");
		out.write("			</ul>");
		out.write("			<p><input type=\"submit\"  value=\"µ¡¼À\" /></p>");
		out.write("	</form>");
		out.write("</body>");
		out.write("</html>");
		
		
	}

}
