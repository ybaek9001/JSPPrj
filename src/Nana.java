import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

//annotation 방식
@WebServlet("/hi")

public class Nana extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		/*int x = 3;
		int y = 5;
		int result = x + y;
		System.out.println(result);*/

		//OutputStream os = response.getOutputStream();
		//PrintWriter out = new PrintWriter(os, true);
		
		/*PrintWriter out = response.getWriter();
		
		for(int i=0; i<100; i++)
		{
			out.println((i+1)+": 안녕 Servlet!!<br />");
		}
		*/
		
		/*out.printf("result</br>%d",result);
		out.close();
		os.close();*/
		
		String _cnt = request.getParameter("cnt");
		
		int cnt = 2;
		if(_cnt != null && !_cnt.equals(""))
			cnt = Integer.parseInt(_cnt);
		
		PrintWriter out = response.getWriter();
		
		for(int i=0; i<cnt; i++)
			out.printf("나루토<br />");
		
		out.close();
		
		
		
		
		
		
		
		
		
		
		
	}

}
