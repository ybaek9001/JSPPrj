package com.newlec.webprj.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newlec.webprj.dao.MemberDao;
import com.newlec.webprj.vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinusController {

	@Autowired
	private MemberDao memberDao;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {

		return "joinus/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@Param("User-name") String userName, 
			String Password, 
			Model model, 
			HttpSession session) throws SQLException {

		Member m = memberDao.getMember(userName);

		String error = null;

		if (m == null)
			error = "사용자 아이디 ㄴㄴ";
		else if (!m.getPwd().equals(Password))
			error = "사용자 비밀번호 ㄴㄴ";

		if (error != null) {
			model.addAttribute("error", error);
			return "joinus/login";
		}

		// 인증 성공
		session.setAttribute("mid", userName); // 인증한 결과 세션에 저장

		return "redirect:../customer/notice";
	}
}