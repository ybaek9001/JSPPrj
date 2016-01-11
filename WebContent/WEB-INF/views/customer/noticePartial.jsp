<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach var="n" items="${list}">
	<!-- <tr id="row-template" class="hidden">
		<td class="seq"></td>
		<td class="title"><a href="noticeDetail?c="></a></td>
		<td class="writer"></td>
		<td class="regdate"></td>
		<td class="hit"></td>
	</tr> -->

	<tr>
		<td class="seq">${n.code}</td>
		<td class="title"><a href="noticeDetail?c=${n.code}">${n.title}</a></td>
		<td class="writer">${n.writer}</td>
		<td class="regdate"><fmt:formatDate pattern="yyyy-MM-dd"
				value='${n.regDate}' /></td>
		<td class="hit">${n.hit}</td>
	</tr>
</c:forEach>