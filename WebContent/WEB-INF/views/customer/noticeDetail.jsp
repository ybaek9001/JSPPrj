<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<h2>공지사항</h2>
<h3 class="hidden">방문페이지위치</h3>
<ul id="breadscrumb" class="block_hlist">
   <li id="home"><a href="">HOME</a></li>
   <li><a href="">고객센터</a></li>
   <li><a href="">공지사항</a></li>
</ul>
<div id="notice-article-detail" class="article-detail margin-large">
   <dl class="article-detail-row">
      <dt class="article-detail-title">제목</dt>
      <dd class="article-detail-data">
         <%-- <%=n.getTitle() %> --%>
         ${notice.title}
      </dd>
   </dl>
   <dl class="article-detail-row">
      <dt class="article-detail-title">작성일</dt>
      <dd class="article-detail-data">
         <%-- <%=n.getRegDate() %> --%>
         <fmt:formatDate pattern="yyyy-MM-dd" value="${notice.regDate}"/>
      </dd>
   </dl>
   <dl class="article-detail-row half-row">
      <dt class="article-detail-title">작성자</dt>
      <dd class="article-detail-data half-data">
         <%-- <%=n.getWriter() %> --%>
         ${notice.writer}
      </dd>
   </dl>
   <dl class="article-detail-row half-row">
      <dt class="article-detail-title">조회수</dt>
      <dd class="article-detail-data half-data">
         <%-- <%=n.getHit() %> --%>
         ${notice.hit}
      </dd>
   </dl>
   <dl class="article-detail-row">
      <dt class="article-detail-title">첨부파일</dt>
      <dd class="article-detail-data">
         <%-- <% for(NoticeFile nf : files) { %>
                           <a href="upload/<%=nf.getSrc()%>"><%=nf.getSrc()%></a>
                        <% } %> --%>
      </dd>
   </dl>

   <div class="article-content">
      <%-- <%=n.getContent()%> --%>
      ${notice.content}
   </div>
</div>
<p class="article-comment margin-small">
   <a class="btn-list button" href="notice">목록</a> <a
      class="btn-edit button" href="noticeEdit">수정</a> <a
      class="btn-del button" href="noticeDel?c=${notice.code}">삭제</a>
</p>
<div class="margin-small" style="border-top: 1px solid #dfdfdf;">
   <dl class="article-detail-row">
      <dt class="article-detail-title">▲ 다음글</dt>
      <dd class="article-detail-data">
      <c:if test="${next.code == null}">
      	<span>다음 글이 없습니다.</span>
      </c:if>
      <c:if test="${next.code != null}">
      <a href="noticeDetail?c=${next.code}">${next.title}</a>
      </c:if>
      </dd>
   </dl>
   <dl class="article-detail-row">
      <dt class="article-detail-title">▼ 이전글</dt>
      <dd class="article-detail-data"><a href="noticeDetail?c=${prev.code}">${prev.title}</a></dd>
   </dl>
</div>
