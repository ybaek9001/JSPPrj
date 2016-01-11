/**
 * 작성일:2016-01-11
 * 작성자:ybaek9001
 * copyright: all granted
 */

function slideUp(
		target, /* <- #notice tbody*/
		to){
	
	if(typeof target == "string")
		target = document.querySelector(target);
	if(to == undefined)
		to = target.clientHeight;
	//var table = document.querySelector("#notices");
	//alert(table.clientHeight);
	
	//이부분은 미리 설정되어 있어야 할 정적인 스타일이므로 cotent폴더 안에있는 css파일에 옮김
	//tbody.style.display="block";
	//tbody.style.position = "relative";
	
	//테이블 높이를 고정하는 것은 스크립트 초기화 블록으로 이동
	//table.style.height = table.clientHeight+"px";
	
	//tbody.style.background = "yellow";
	
	var trs = target.querySelectorAll("tr");
	
	for (var i = 0; i < trs.length; i++)
		trs[i].style.position = "absolute";
	//trs[1].style.top="29px";
	
	//위로 슬라이드
	var top = 0;
	var topId = setInterval(function() {
		// 애니메이션을 위한 프레임 연산 //
		top -= 1;
		for (var i = 0; i < trs.length; i++)
			trs[i].style.top = (i * 31) + top + "px";
	
		console.log("top:" + top);
		if (top <= to) //-100이 될때까지 스크롤
		{
			clearInterval(topId);
		}
	}, 10);
}