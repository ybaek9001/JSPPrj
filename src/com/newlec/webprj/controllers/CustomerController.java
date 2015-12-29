package com.newlec.webprj.controllers;

import java.io.PrintWriter;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newlec.webprj.dao.NoticeDao;
import com.newlec.webprj.dao.mybatis.MyBatisNoticeDao;
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
	public String notice(PrintWriter out, Model model, HttpServletRequest request){
		out.println("hello!!!!!!!!!!!!!!!!");
		/*NoticeDao dao = new MyBatisNoticeDao();*/
		List<Notice> list = noticeDao.getNotices(1, "Title","");
		
		
		//다음 페이지로 request를 전달
		model.addAttribute("list", list);
		/*model.addAttribute("n", list);*/
		//request.setAttribute("list", list);
		
		
		
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
	public String noticePartial(String p, Model model){
		int page = 1;
		if(p!=null && !p.equals(""))
			page = Integer.parseInt(p);
		
		List<Notice> list = noticeDao.getNotices(page, "TITLE", "");
		
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
		
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		
		int size = list.size();
		for (int i = 0; i < list.size(); i++) {
			Notice n = list.get(i);
			if (i+1 == size)
				builder.append(
						String.format("{'code':'%s', 'title':'%s', 'writer':'%s'"
								+ ", 'regdate':'%s', 'hit':'%s'}"
								, n.getCode(), n.getTitle(), n.getWriter(), n.getRegDate(), n.getHit()));

			else
				builder.append(
						String.format("{'code':'%s', 'title':'%s', 'writer':'%s'"
								+ ", 'regdate':'%s', 'hit':'%s'}, "
								, n.getCode(), n.getTitle(), n.getWriter(), n.getRegDate(), n.getHit()));
		}
		
		
		builder.append("]");
		
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		out.println(builder.toString());
	
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
	
	@RequestMapping("noticeDel")
	public String noticeDel(String c){
		
		noticeDao.delete(c);
		return "redirect:notice";
	}
	
	
}
