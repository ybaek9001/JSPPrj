/**
 * 
 */

window.addEventListener("load",function(){
	var curImg = null;
	var board = document.querySelector("#board");
	var pos={x:0, y:0};
	var dragging = false;
	
	//alert(chatOutput.clientWidth);
	document.onmousedown = function(event){
		curImg = event.target
		pos.x=event.clientX;
		pos.y=event.clientY;
		
		dragging = true;
	}
	
	board.onmouseup = function(event){
		dragging = false;
	}
	
	document.onmousemove = function(event){
		if(curImg!=null && dragging){
		console.log(event.x);
		curImg.style.left = event.clientX-pos.x +"px";
		curImg.style.top = event.clientY-pos.y +"px";
		event.preventDefault();
		}
	}	
});

var wsocket;

window.addEventListener("load", function(){
	//현재 페이지를 구성하는 객체 또는 변수들 선언
	
	var uid="waytogo";
	var btnConn=document.querySelector("#btn-conn");
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
	btnConn.onclick = function(event){
		if(wsocket==undefined)
		{
			//wsocket = new WebSocket("ws://211.238.142.248/JSPPrj/content/chatserver")
			wsocket = new WebSocket("ws://211.238.142.115:8080/JSPPrj/content/chatserver")
			wsocket.onopen = sockOpen;
			wsocket.onclose = sockClose;
			wsocket.onmessage = sockMessage;
		}
	}
	
	function sockOpen(){
		alert("접속 되었습니다");
	}
	
	function sockClose(){
		alert("접속이 끊겼습니다");
	}
	
	function sockMessage(event){
		//alert(event.data);
		var data = JSON.parse(event.data);
		switch(data.type)
		{
		case "chat":
			printMessage(data.uid, data.msg);
			break;
		case "draw":
			break;
		}
		
	}
	
	btnSend.onclick = function(event){
		//var userName ="newlec";
		var msg = inputBox.value;
		var data = {type:"chat", uid:uid, msg:msg};
		//alert(JSON.stringify(data));
		data = JSON.stringify(data);
		wsocket.send(data);
		//printMessage(userName,msg);
		
		//텍스트 입력 상자의 문자열을 지우는 코드
		inputBox.value="";
		
	}
	
	
	inputBox.onkeyup = function(e){	//onkeydown, onkeypress
		if(e.keyCode == 13){
			var event = document.createEvent("MouseEvent");	//event type, bubbles, cancelable
			event.initEvent("click",true,true)	
			
			btnSend.dispatchEvent(event);
		}
	}
	
	
	
})