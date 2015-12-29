package com.newlec.webprj.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.ui.Model;

public class NewlecAuthFailHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("����� ���� ����!");
					
		request.setAttribute("error", "���̵�, ��й�ȣ ����");
		
		response.sendRedirect("/joinus/login?error=1");
		//request.getRequestDispatcher("/joinus/login").forward(request, response);
		
		//super.onAuthenticationFailure(request, response, exception);
	}
}
