<%@page import="com.newlec.webprj.dao.mybatis.MyBatisNoticeFileDao"%>
<%@page import="com.newlec.webprj.dao.NoticeFileDao"%>
<%@page import="com.newlec.webprj.vo.NoticeFile"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.newlec.webprj.vo.Notice"%>
<%@page import="com.newlec.webprj.dao.mybatis.MyBatisNoticeDao"%>
<%@page import="com.newlec.webprj.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%

  ServletContext ctx = request.getServletContext();
  String path = ctx.getRealPath("/customer/upload");
  
  /* title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
  content = new String(content.getBytes("ISO-8859-1"),"UTF-8"); */

//파일을 가져오기 위해 Request를 MultipartRequest로 사용함
  MultipartRequest req = new MultipartRequest(
	   request
	   , path
	   , 1024*1024*10
	   , "UTF-8"
	   , new DefaultFileRenamePolicy() );
  /* request인자설정, 파일저장을 위한 경로 path, 용량설정, 인코딩방식설정, 동일한 이름 처리방식  */  

  
  /*-- notice info -------------------------------------------- */  	
  String title = req.getParameter("title");
  String content = req.getParameter("content");

  NoticeDao noticeDao = new MyBatisNoticeDao();
  Notice n = new Notice();
  n.setTitle(title);
  n.setContent(content);
  n.setWriter("newlec"); //나중에는 로그인 된 회원 아이디를 박을거임
  
  int result = noticeDao.insert(n);
  
  
  /*-- noticeFile info -------------------------------------------- */
  String src = req.getOriginalFileName("file"); /* - 원본 파일 이름 */
  
  NoticeFile file = new NoticeFile();
  file.setNoticeCode(noticeDao.getLastCode());
  file.setSrc(src);
  
  NoticeFileDao fileDao = new MyBatisNoticeFileDao();
  fileDao.insert(file);
  
  if(result==1){
   response.sendRedirect("notice.jsp"); //response는 내장객체여서 선언안하고 써도됨 / sendRedirect는 페이지로 보내주는거
}
%>

result = <%=result%>