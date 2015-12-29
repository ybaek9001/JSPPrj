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
@RequestMapping("/customer/") //Ŭ���� ��ü�� customer ��� �ο�
public class CustomerController {

	@Autowired //ioc�����̳ʿ���, �� Application-context�������� ��ϵǵ��� ����
	//private�� ��ü�� ���� set �����ϰ� �ϴ� ���� Autowired�ε�, setter�� �ȸ�����ִ� ������ �����ص� �����⋚����?
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
		
		
		//���� �������� request�� ����
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
	
	/*form�� �������ִٸ�, ���� �����ϴ� ȭ��� summit���� ���� ȭ�� �� ������ �������־�� �Ѵ�.*/
	@RequestMapping(value="noticeReg", method=RequestMethod.GET)
	public String noticeReg(HttpSession session){
		
		/*if(session.getAttribute("mid")==null)
			return "redirect:../joinus/login?returnUrl=/customer/noticeReg";*/
		
		return "customer/noticeReg"; //GET��û�� ó���� View����
	}	
	
	@RequestMapping(value="noticeReg", method=RequestMethod.POST)
	public String noticeReg(Notice n, Principal principal) throws SQLException{
		/*String title, String content - post���� �Ѱ��� value�� �̸��� ���ٸ� �ڵ����� ����ش�.
		System.out.printf("title : %s / content : %s", title, content);*/
		
		n.setWriter(principal.getName()); //���� �α����� ������� �̸�		
		noticeDao.insert(n);
		System.out.printf("%s %s", n.getTitle(), n.getContent());		
		/*return "notice";*/ //����ڰ� post�� �������Ƿ�, �۸������ �̵��ؾ� ��.
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
