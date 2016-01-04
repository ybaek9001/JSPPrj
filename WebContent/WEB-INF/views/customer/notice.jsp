
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<h2>공지사항</h2>
<h3 class="hidden">방문페이지 로그</h3>
<ul id="breadscrumb" class="block_hlist clear">
	<li>HOME</li>
	<li>고객센터</li>
	<li>공지사항목록</li>
</ul>
<h3 class="hidden">공지사항 목록</h3>
<form id="content-searchform" class="article-search-form"
	action="notice.jsp" method="get">
	<fieldset>
		<legend class="hidden"> 목록 검색 폼 </legend>
		<input type="hidden" name="pg" value="" /> <label for="f"
			class="hidden">검색필드</label> <select name="f">
			<option value="TITLE">제목</option>
			<option value="CONTENT">내용</option>
		</select> <label class="hidden" for="q">검색어</label> <input type="text" name="q"
			value="" /> <input type="submit" value="검색" />
	</fieldset>
</form>

<script src="../content/js/ui.js"></script>

<script>
   var datas; /* =[
         {code:1, title:'<b>자바스크립트 이란?</b>', writer:'waytogo'},
         {code:2, title:'DOM 이란?', writer:'waytogo'},
         {code:3, title:'Ajax를 이용하는 방법', writer:'waytogo'}
   ]; */
   
   
   function dataBind(row, data)
   {
      var tds=row.querySelectorAll("td");
      var keys=["code","title","writer","regdate","hit"];
      //1.innerText 텍스트(태그있으면 #제외하고 텍스트만)
      //2.innerHTML 표준화 x (안되는 브라우저 많겟지?)
      //3.content 표준화
      
      for(var i=0; i<tds.length;i++)
      {
         tds[i].innerHTML = data[keys[i]];
      }
   }
   

   
	window.onload = function() 
	{
		//---------------<Ajax POST Method>------------------------------//
		var btnWrite = document.querySelector("#btn-write");
		btnWrite.onclick = function()
		{
			/* //전체 틀을 만들기 위한 설정
			var dlg = document.createElement("div");
			dlg.style.width = "100%";
			dlg.style.height ="100%";
			dlg.style.position = "fixed";
			dlg.style.top = "0px";
			
			//위에 만들었던 틀 안에 검은색 반투명한  영역 설정
			var screen = document.createElement("div");			
			screen.style.background = "black";
			screen.style.opacity = "0.5";
			screen.style.width = "inherit";
			screen.style.height ="inherit";
			
			//위에 만들었던 틀 안에 하얀색 영역(글 등록 form이 들어갈 곳) 설정
			var container = document.createElement("div");			
			container.style.background = "#fff";
			container.style.width = "720px";
			container.style.height = "550px";
			container.style.position = "fixed";
			container.style.top = "100px";
			container.style.left = "300px";
			
			
			document.body.appendChild(dlg);
			
			dlg.appendChild(screen);
			dlg.appendChild(container);
			 */
		var dlg = showDialog(		// content\js\ui.js로 리펙토링한 곳 안에 있는 함수 호출
				"noticeRegPartial",	//url
				".btn-save", 	    //btn-selector
				function(){			//btn-handler
					//데이터 수집
					var title = dlg.querySelector("input[name='title']").value;
					//alert(title);
					var content = dlg.querySelector("#txtContent").value;
					//alert(content);
					
					var data = "title="+title+"&content="+content;//urlencoded
					//-------------------------------------------------
					var	request = new XMLHttpRequest();
					
					request.open("POST", "noticeReg", true); //true:비동기 방식
					//----------------------------------------
					request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					request.setRequestHeader("Contnet-length", data.length);
					request.setRequestHeader("Connection", "close");
					//---------------------------------------
					request.send(data); 
							
					request.onreadystatechange = function()
					{
						if (request.readyState == 4 && request.status == 200) 
						{
							if(request.responseText == "ok")
							alert("성공");
							
							var page = event.target.innerText;	
							
							//-------------------XMLHttpRequest 준비---------------------//
							var	request = new XMLHttpRequest();
							
							request.open("GET", "noticePartial?p=1", true); //true:비동기 방식
							request.send(null); 
									
							request.onreadystatechange = function()
							{
								if (request.readyState == 4) 
								{
									//tbody의 안쪽 방을 비우기
									//alert(request.responseText);
									var tbody = document.querySelector("#notices tbody");
									tbody.innerHTML=request.responseText;
								}
							}
						}
					} 
					//---------------------------------------------
					
					//데이터 전송
					
					closeDialog(dlg);
					return false;
				});
			
			
			
	//---------------<<Ajax로 Form 추가하기>-------------------------------------------------//
			
			/* //-------------------XMLHttpRequest 준비---------------------//
			var	request = new XMLHttpRequest();
			
			request.open("GET", "noticeRegPartial", true); //true:비동기 방식
			request.send(null); 
					
			request.onreadystatechange = function()
			{
				if (request.readyState == 4) 
				{
					//tbody의 안쪽 방을 비우기
					container.innerHTML=request.responseText;
					var btnSave = container.querySelector(".btn-save");
					btnSave.onclick = function(){
						alert("test");
						return false;
					}
				}
			} 
			//---------------------------------------------------------//
			*/
			return false;
		}
		
		
		
		
		
		//---------------<Ajax GET Method>------------------------------//
		//페이지 숫자 클릭하면 Ajax를 이용해서 해당부분만 바뀜
		var nums = document.querySelectorAll("#pager-wrapper ul a");
		
		var numClick = function(event)
		{
			var page = event.target.innerText;	
			
			//-------------------XMLHttpRequest 준비---------------------//
			var	request = new XMLHttpRequest();
			
			request.open("GET", "noticePartial?p="+page, true); //true:비동기 방식
			request.send(null); 
					
			request.onreadystatechange = function()
			{
				if (request.readyState == 4) 
				{
					//tbody의 안쪽 방을 비우기
					//alert(request.responseText);
					var tbody = document.querySelector("#notices tbody");
					tbody.innerHTML=request.responseText;
				}
			}
			//---------------------------------------------------------//
			
			return false;
		}
		
		for(var i=0; i<nums.length; i++)
			nums[i].onclick=numClick;
			
		
		var notices = document.getElementById("notices");

		//-------------------------------행복제---------------------------------//
		var btnRowClone = document.querySelector("input[value='행복제']");
		btnRowClone.onclick = function() 
		{
			/* var datas=[
				          {code:1, title:'<b>자바스크립트란?</b>', writer:'newlec'},
				          {code:2, title:'DOM이란?', writer:'newlec'},
				          {code:3, title:'Ajax를 이용하는 방법은?', writer:'newlec'}
			          ]; */
			
			var template = document.querySelector("#row-template");

			for ( var i in datas) 
			{
				//cloneNode 는 하위에 있는 모든 노드를 복사하는 메소드, 
				//true: 모든 자식 노드가 재귀적으로 복제되어 원래 객체의 문서 트리와 정확히 일치하는 복사본이 만들어짐
				var clone = template.cloneNode(true);	
				clone.className = "";	//밑에 class="hidden"된 것을 해제
				//클론한 틀에 데이터를 심기
				dataBind(clone, datas[i]);
				
				notices.querySelector("tbody").appendChild(clone);
			}
		};
		
		//----------------------------------행추가--------------------------------//
		
				/* XMLHttpRequest를 사용한 Ajax 프로그램은 다음과 같이 세 과정을 거치게 된다.
		
				1.XMLHttpRequest 객체 구하기
				
				2.웹 서버에 요청 전송하기
				
				3.웹 서버에서 응답이 도착하면 화면에 반영하기 */
		
		var img = null;
		var page=0;
		
		var btnRowAdd = document.querySelector("input[value='행추가']");
		btnRowAdd.onclick = function()
		{
			if(img!=null)
			{
				alert("현재 처리중입니다")
				return;
			}
			
			
			
			var request;

			if (window.ActiveXObject) 
			{
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} 
			else if (window.XMLHttpRequest)
			{
				request = new XMLHttpRequest();
			}
			
			page++;
			request.open("GET", "noticeJSON?p="+page, true); //true:비동기 방식
			request.send(null); // 데이터 요청..동기식으로 하면 여기서 freezing됨, 근데 비동기식으로 하면 eval할때 빈데이터가 와서 에러...그럼 어떻게??
					
			request.onreadystatechange = function()
			{
				if (request.readyState == 4) 
				{
					datas = eval(request.responseText);

					var template = document.querySelector("#notice-row");
					
					for ( var i in datas) 
					{
						//document.importNode: 현재 문서가 아닌 외부 문서의 노드를 복사하여 현재 문서에 넣을 수 있도록 해줌
						var clone = document.importNode(template.content, true); //.content: 템플릿의 내부를 포함하는 읽기 전용의 DocumentFragment

						dataBind(clone, datas[i]);
						notices.querySelector("tbody").appendChild(clone);
					}
					
					//행(데이터)이 추가되었으니까 ajax img를 제거
					document.body.removeChild(img);
					img = null;
				}
			}

			
			//이미지를 띄우는 장소
			img = null;
			img = document.createElement("img");
			img.src = "../content/images/ajax-loader.gif";
			img.style.position = "absolute";
			img.style.left ="200px";
			img.style.top ="400px";
			
			document.body.appendChild(img);
		};
	};
</script>

<table id="notices" class="article-list margin-small">
	<caption class="hidden">공지사항</caption>
	<thead>
		<tr>
			<th class="seq">번호</th>
			<th class="title">제목</th>
			<th class="writer">작성자</th>
			<th class="regdate">등록일</th>
			<th class="hit">조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="n" items="${list}">
			<tr id="row-template" class="hidden">
				<td class="seq"></td>
				<td class="title"><a href="noticeDetail?c="></a></td>
				<td class="writer"></td>
				<td class="regdate"></td>
				<td class="hit"></td>
			</tr>

			<tr>
				<td class="seq">${n.code}</td>
				<td class="title"><a href="noticeDetail?c=${n.code}">${n.title}</a></td>
				<td class="writer">${n.writer}</td>
				<td class="regdate"><fmt:formatDate pattern="yyyy-MM-dd"
						value='${n.regDate}' /></td>
				<td class="hit">${n.hit}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<template id="notice-row">
<tr>
	<td class="seq"></td>
	<td class="title"><a href="noticeDetail?c="></a></td>
	<td class="writer"></td>
	<td class="regdate"></td>
	<td class="hit"></td>
</tr>
</template>

<p class="article-comment margin-small">
	<input type="button" value="행복제" /> 
	<input type="button" value="행추가" />
	<input type="button" value="데이터요청" />
	<%-- <security:authorize ifAnyGranted="ROLE_ADMIN"> --%>
		<a id="btn-write" class="btn-write button" href="noticeReg">글쓰기</a>
	<%-- </security:authorize> --%>
</p>

<p id="cur-page" class="margin-small">
	<span class="strong">1</span> / 10 page
</p>

<div id="pager-wrapper" class="margin-small">
	<div class="pager clear">
		<p id="btnPrev">
			<a class="button btn-prev" href="notice.jsp">이전</a>
		</p>
		<ul>
			<li><a class="strong" href="notice?p=1">1</a></li>
			<li><a href="notice?p=2">2</a></li>
			<li><a href="notice?p=3">3</a></li>
			<li><a href="notice?p=4">4</a></li>
			<li><a href="notice?p=5">5</a></li>
		</ul>
		<p id="btnNext">
			<span class="button btn-next">다음</span>
		</p>
	</div>
</div>