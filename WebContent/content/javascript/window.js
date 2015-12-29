/**
 * 
 */

window.addEventListener("load", function(){
	
	var newwin;
	
	var btnOpen=document.getElementById("btn-open");
	btnOpen.onclick=function(){
		newwin=open("newwin.html","_blank","width=20",true);
		
	};
	var btnMove=document.getElementById("btn-move");
	btnMove.onclick=function(){
		newwin.moveTo(100,100);
	}
});

//window.addEventListener("load", init);