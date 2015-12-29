/**
 * 
 */

function init()
{
	var btnSum=document.getElementById("btn-sum");
	btnSum.onclick=btnSumClick;
}

function btnSumClick()
{
	var txtX=document.getElementById("txt-x");
	var txtY=document.getElementById("txt-y");
	var txtSum=document.getElementById("txt-sum");
	
	var x=parseInt(txtX.value);
	var y=parseInt(txtY.value);
	txtSum.value=x+y;
	//alert("Hello javascript");
}

window.onload=init;