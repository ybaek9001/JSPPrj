<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
/* NoticeDao dao = new MyBatisNoticeDao();
List<Notice> list = dao.getNotices(1, "Title",""); */
%>

 <c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <title>index</title>
      <link href="${ctx}/content/customer/css/customer.css" type="text/css" rel="stylesheet" />
      <link href="${ctx}/content/customer/css/<tiles:getAsString name="css" />" type="text/css" rel="stylesheet" />
      
   </head>
   <body>
      <div id="header">
         <div class="top-wrapper">
         <!-- 헤더부분 -->    
         <tiles:insertAttribute name="header" />
         </div>
      </div>
      <div id="visual" class="customer">
         <div class="top-wrapper">

         </div>
      </div>
      <div id="main">
         <div class="top-wrapper clear">
            <div id="content">
                <!-- 컨텐트 부분 -->
                <tiles:insertAttribute name="content" />
            </div>
            <div id="navi">
               <!-- 어사이드 부분 -->
               <tiles:insertAttribute name="aside" />
            </div>
         </div>
      </div>
      <div id="footer">
         <div class="top-wrapper">
            <!-- 푸터 부분 -->
            <tiles:insertAttribute name="footer" />
         </div>
      </div>
   </body>
</html>