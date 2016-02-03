package com.newlec.webprj.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.newlec.webprj.dao.NoticeDao;
import com.newlec.webprj.vo.Notice;

@Controller
@RequestMapping("/customer/") //클래스 전체에 customer 경로 부여
public class CustomerController {

	@Autowired //ioc컨테이너에서, 즉 Application-context단위에서 등록되도록 선언
	//private인 객체로 직접 set 가능하게 하는 것이 Autowired인데, setter를 안만들어주는 이유는 접근해도 괜찮기떄문에?
	private NoticeDao noticeDao;
	
	/*@Autowired
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}*/

	@RequestMapping("notice")
	public String notice(PrintWriter out, Model model, HttpServletRequest request, String pg, String f, String q){
		out.println("hello!!!!!!!!!!!!!!!!");
		/*NoticeDao dao = new MyBatisNoticeDao();*/
		//List<Notice> list = noticeDao.getNotices(1, "Title","");
		int page = 1;
		String field = "TITLE";
		String query = ""; 
		
		if(pg!=null && !pg.equals(""))
			page=Integer.parseInt(pg);
		if(f!=null && !f.equals(""))
			field=f;
		if(q!=null && !q.equals(""))
			query=q;
		
		List<Notice> list = noticeDao.getNotices(page, field, query);
		int recordCount = noticeDao.getNoticeCount(field,query);
		
		//다음 페이지로 request를 전달
		model.addAttribute("list", list);
		/*model.addAttribute("n", list);*/
		//request.setAttribute("list", list);
		
		model.addAttribute("recordCount", recordCount);
		System.out.println(recordCount);
		
		/*for(Notice n : list){
			out.println("title : " + n.getTitle());
		}*/
		
		return "customer/notice";
	}
	
	@RequestMapping("noticeRegPartial")
	public String noticeRegPartial(){

		return "/customer/noticeRegPartial";
	}
	
	@RequestMapping("noticePartial")
	public String noticePartial(String pg,String f, String q, Model model){
		int page = 1;
		String field = "TITLE";
		String query = ""; 
		
		if(pg!=null && !pg.equals(""))
			page=Integer.parseInt(pg);
		if(f!=null && !f.equals(""))
			field=f;
		if(q!=null && !q.equals(""))
			query=q;
		
		List<Notice> list = noticeDao.getNotices(page, field, query);
		
		
		model.addAttribute("list", list);
		
		
		return "/customer/noticePartial";
		//return "/WEB-INF/views/customer/noticePartial.jsp";
	}
	
	@RequestMapping("noticeJSON")
	public void noticeJSON(String p, PrintWriter out){
		//String data = "[{},{}]";
		
		int page = 1;
		if(p!=null && !p.equals(""))
			page = Integer.parseInt(p);
		
		List<Notice> list = noticeDao.getNotices(page, "TITLE", "");
		//List<Notice> list2 = noticeDao.getNotices(page, "TITLE", "");
		
		
		//List array = new ArrayList<List>();
		//array.add(list);
		//array.add(list2);
		
		Gson gson = new Gson();
		//gson.toJson(list2);
		out.println(gson.toJson(list));
		
		/*StringBuilder builder = new StringBuilder();
		builder.append("[");
		int size = list.size();
		for (int i = 0; i < list.size(); i++) {
			Notice n = list.get(i);
			//builder.append(gson.toJson(n));
			builder.append(
				String.format("{\"code\":\"%s\", \"title\":\"%s\", \"writer\":\"%s\", \"regdate\":\"%s\", \"hit\":\"%s\"}"
							, n.getCode(), n.getTitle(), n.getWriter(), n.getRegDate(), n.getHit()));
			if (i < size-1)
				builder.append(", ");		
		}
		builder.append("]");
		
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		out.println(builder.toString());*/
	
	}
	
	@RequestMapping("noticeDetail")
	public String noticeDetail(String c, Model model) {
		Notice notice = noticeDao.getNotice(c);		
		Notice prev = noticeDao.getPrevNotice(c);
		Notice next = noticeDao.getNextNotice(c);
		
		model.addAttribute("notice", notice);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		return "customer/noticeDetail";
	}
	
	/*form을 가지고있다면, 폼을 제공하는 화면과 summit했을 떄의 화면 두 가지를 정의해주어야 한다.*/
	@RequestMapping(value="noticeReg", method=RequestMethod.GET)
	public String noticeReg(HttpSession session){
		
		/*if(session.getAttribute("mid")==null)
			return "redirect:../joinus/login?returnUrl=/customer/noticeReg";*/
		
		return "customer/noticeReg"; //GET요청을 처리할 View제공
	}	
	
	@RequestMapping("noticeRegAjax")
	//public void noticeRegAjax(@RequestParam("p[]")String[] p, Notice n, PrintWriter out){
	//map 형식이 아닌 요청 body를 그대로 사용하기 위해 @RequestBody를 추가함
	//반드시 JSON 해석기 중 하나(GSON..)를 lib로 포함해 줘야 함
	//그렇지 않으면 request 415에러가 발생 함
	public void noticeRegAjax(@RequestBody Notice n, PrintWriter out, Principal principal) throws SQLException{
		/*System.out.println("received:" + p.length);
		System.out.println("P[1]:" + p[1]);*/
		//System.out.println(n.getTitle());
		//out.println(n.getTitle());
		n.setWriter("waytogo");
		int result = noticeDao.insert(n);
		
		if(result>=1)
			out.write("ok");
		else
			out.write("error");
		
		//데이터를 처리하는 코드
		/*if(p!=null)
			out.write("ok");
		else
			out.write("fail");*/
	}
	
	@RequestMapping(value="noticeReg", method=RequestMethod.POST)
	public String noticeReg(Notice n, Principal principal) throws SQLException{
		/*String title, String content - post에서 넘겨준 value와 이름이 같다면 자동으로 담아준다.
		System.out.printf("title : %s / content : %s", title, content);*/
		
		
		n.setWriter(principal.getName()); //현재 로그인한 사용자의 이름		
		noticeDao.insert(n);
		System.out.printf("%s %s", n.getTitle(), n.getContent());		
		/*return "notice";*/ //사용자가 post를 보냈으므로, 글목록으로 이동해야 함.
		return "redirect:notice";
	}
	
	@RequestMapping("noticeEdit")
	public String noticeEdit(String code, HttpSession session){
		/*if(session.getAttribute("mid")==null)
			return "redirect:../joinus/login?returnUrl=/customer/noticeEdit";*/
		
		return "customer/noticeEdit";
	}
	
	@RequestMapping("noticeDelAjax")
	public void noticeDelAjax(@RequestBody String[] codes, PrintWriter out)
	{
		int result = 0;
		for(int i=0;i<codes.length;i++)
		 result += noticeDao.delete(codes[i]);
		
		if(result == codes.length)
			out.write("ok");
		else
			out.write("error");
	}
	
	@RequestMapping("noticeDel")
	public String noticeDel(String c){
		
		noticeDao.delete(c);
		return "redirect:notice";
	}
	
	
	//단일 파일 업로드를 위한 서버 코드 with 스프링
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("notice-title")String titleTitle,
			MultipartFile file, HttpServletRequest request) throws IOException{	//@ResponseBody 리턴타입이 view를 불러오는 스트링이 아니기 때문에
		
		
		//파일을 저장할 디렉토리와 경로 설정
		ServletContext context = request.getServletContext();
		String rootPath = context.getRealPath("/customer")+"\\upload";
		
		File f = new File(rootPath);
		if (!f.exists()) {
			if (f.mkdir()) {
				System.out.println("Directory is created!!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
	    
		//전송된 파일을 저장하는 로직
		String fileName = file.getOriginalFilename();
		byte[] buf = file.getBytes();
		
		//저장할 파일이 있는지 없는지 검사를 해야만 하지만 수업에서 생략했음
		FileOutputStream fout = new FileOutputStream(rootPath+File.separator+fileName); //File.separator == "\\"
		fout.write(buf);
		fout.close();
		
		return "You successfully uploaded file=" + rootPath+File.separator+fileName;
		//return "redirect:../content/javascript/app/views/fileupload.html";
	}
	
	
}
