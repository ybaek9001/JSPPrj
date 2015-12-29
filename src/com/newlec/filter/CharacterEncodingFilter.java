package com.newlec.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	
	private String encoding;
	public FilterConfig filterConfig;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터 실행");
		
		//web.xml에 지정해놓은 encoding으로 설정해줌
		request.setCharacterEncoding(encoding);
		
		//filter실행 후 다음 페이지에게 request와 response를 넘겨줌으로써 올바르게 페이지가 보이도록 함.
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
		
	}
	
	@Override
	public void destroy() {
		
	}
}
