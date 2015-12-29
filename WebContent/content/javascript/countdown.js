/**
 * 
 */


var id;

function init()
{
	//var id;
	var btnCountDown=document.getElementById("btn-countdown");
	btnCountDown.onclick=function(){
		
		var lblCount=document.getElementById("lbl-count");
		if(id==undefined || parseInt(lblCount.innerText)>0)
			id=setTimeout(countDown,1000);
		//id=setInterval(countDown,1000);
	};
	
	/*var btnCountStop=document.getElementById("btn-countstop");
	btnCountStop.onclick=function(){
		
		//clearInterval(id);
		clearTimeout(id);
	}*/
}

function stop(){
	var btnCountStop=document.getElementById("btn-countstop");
	btnCountStop.onclick=function(){
		
		//clearInterval(id);
		clearTimeout(id);
	}
}

function countDown(){
	
	var lblCount=document.getElementById("lbl-count");
	lblCount.innerText=parseInt(lblCount.innerText)-1;
	if(parseInt(lblCount.innerText)>0)
		id=setTimeout(countDown,1000);
	
}

window.addEventListener("load", init);
window.addEventListener("load", stop);
//window.onload=init;