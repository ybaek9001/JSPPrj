/**
 * 
 */



window.addEventListener("load", function(){
	//현재 페이지를 구성하는 객체 또는 변수들 선언
	var btnSend=document.querySelector("#chat-input-panel .btn");
	var outputList=document.querySelector("#chat-output ul");
	var inputBox=document.querySelector("#chat-input-panel textarea");
	var chatOutput = document.querySelector("#chat-output")
	//현재 페이지에서 사용하는 서비스 처리 함수들
	function printMessage(userName, msg){
		
		var template = document.querySelector("#chat-template");
		var clone = document.importNode(template.content, true)
		//alert(inputBox);
		//clone 한 틀에 데이터를 심자!!
		var userNameBox = clone.querySelector("li > div > div:first-child");
		var msgBox = clone.querySelector("li > div > div:last-child");
		
		userNameBox.innerText = userName;
		msgBox.innerText = msg;
		//alert(outputList);
		outputList.appendChild(clone);
		chatOutput.scrollTop = chatOutput.scrollHeight;
	}
	
	
	//현재 페이지에서 사용하는 이벤트 처리 함수들
	btnSend.onclick = function(){
		var userName ="newlec";
		var msg= inputBox.value;
		
		printMessage(userName,msg);
	}
	
})