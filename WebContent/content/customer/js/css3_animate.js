window.addEventListener("load", function(){
	var btnRotate = document.querySelector("input[value='회전']")
	btnRotate.onclick = function(){
		var img = document.querySelector("#canvas img");
		/*img.style.transform = "rotate(7.5deg)";*/
		img.style.opacity=0.5;
	};
});

