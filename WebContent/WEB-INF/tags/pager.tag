<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 현재 페이지 번호를 얻는 연산 -->
<c:if test="${empty param.pg}">
   <c:set var="pg" value="1"/>
</c:if>

<c:if test="${not empty param.pg}">
   <c:set var="pg" value="${param.pg}"/>
</c:if>   

<!-- 시작페이지 번호를 얻는 연산 -->
<c:set var="gap" value="${(pg-1)%5}"/>
<c:set var="startNum" value="${pg-gap}"/>
<%-- <div>test : ${startNum}</div> --%>

<!-- 마지막페이지 번호를 얻는 연산 -->
<c:set var="lastNum" value="${(recordCount%10==0)? recordCount/10 : fn:substringBefore(recordCount/10,'.')+1}"/>



<div class="pager clear">      
   <p id="btnPrev">
      <c:if test="${startNum==1}">
         <span class="button btn-prev">이전</span>
      </c:if>
      <c:if test="${startNum!=1}">
         <a class="button btn-prev" href="?pg=${startNum-5}&f=${param.f}&q=${param.q}">이전</a>
      </c:if>
   </p>
   
   <ul>
      <c:forEach var="i" begin="${startNum}" end="${startNum+4}">
      <c:if test="${i<=lastNum}">
      <li>
         <c:if test="${pg==i}">
         <a class="strong" href="?pg=${i}&f=${param.f}&q=${param.q}">${i}</a>
         </c:if>
         <c:if test="${pg!=i}">
         <a href="?pg=${i}&f=${param.f}&q=${param.q}">${i}</a>
         </c:if>
      </li>
      </c:if>
      </c:forEach>         
   </ul>
   
   <p id="btnNext">
      <c:if test="${startNum+4>=lastNum}">
         <span class="button btn-next">다음</span>
      </c:if>
   
      <c:if test="${startNum+4<lastNum}">   
         <a class="button btn-next" href="?pg=${startNum+5}&f=${param.f}&q=${param.q}">다음</a>
      </c:if>
   </p>
</div>