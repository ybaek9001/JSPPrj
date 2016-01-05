/**
 * 작성일: 2015-12-30
 * 작성자:yb
 */

var showDialog = function(url, btnSelector, btnHandler){
	//전체 틀을 만들기 위한 설정
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
	container.style.zIndex = 2;
	
	var closeButton = document.createElement("input");
	closeButton.type="button";
	closeButton.value="X";
	closeButton.style.width="50px";
	closeButton.style.height="50px";
	closeButton.style.position="fixed";
	closeButton.style.left=parseInt(container.style.left)+parseInt(container.style.width)+"px";
	closeButton.style.top=parseInt(container.style.top)-10+"px";
	closeButton.style.zIndex = 1;
	closeButton.onclick = function(){closeDialog(dlg);};
	
	dlg.appendChild(closeButton);
	dlg.appendChild(screen);
	dlg.appendChild(container);	
	
	document.body.appendChild(dlg);
	
	
	//-------------------XMLHttpRequest 준비---------------------//
	var	request = new XMLHttpRequest();
	
	request.open("GET", url, true); //true:비동기 방식
	request.send(null); 
			
	request.onreadystatechange = function()
	{
		if (request.readyState == 4) 
		{
			//tbody의 안쪽 방을 비우기
			container.innerHTML=request.responseText;	//url데이터를 container영역으로 얻어옴(수업 땐 noticeRegPartial글쓰기양식을 얻어옴)
			var btnSave = container.querySelector(btnSelector);	//사용자가 선택한 버튼 셀렉터
			btnSave.onclick = btnHandler;	//사용자가 넘긴 버튼 핸들러(글 작성하고 저장버튼 눌렀을 때 실행 되야 할 function <-notice에서 인자로 넘겨줌)
			
		}
	}
	
	return dlg;
};

var closeDialog = function(dlg){
	document.body.removeChild(dlg)	
};