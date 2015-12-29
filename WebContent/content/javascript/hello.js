/**
 * 
 */

		var notice={code:"1", title:"hello javascript", writer:"newlec"};
		var notices=[
		             {code:"1", title:"hello javascript", writer:"newlec"},
		             {code:"2", title:"what javascript is", writer:"newlec"},
		             {code:"3", title:"ok...", writer:"newlec"}
		             ];
		
		/* alert(notices[1].title)
		alert(notices[1]["title"]) */
		
		/* var keys=["code", "title", "writer"];
		for(var i=0;i<3;i++)
			alert(notice[keys[i]]) */
		
		/* var ar=["철수","영희","맹구"];
		for(var i in ar)
			alert(ar[i]); */
			
		/* var record={kor:30,eng:40,math:50};
		for(var i in record)
			alert(record[i]); */
		
		//var f1=function add(x,y)
			
			
		/* var f1=function(x,y)
		{
			/* return x+y; */
			//alert(arguments.length);
			//return arguments[0]+arguments[1];
		/* }; */
		//alert(add(3,4));
		/* alert(f1(5,6,7)); */
		
		/* var x=(function(x,y)
		{
			return x+y;
		})(5,11);
		
		alert(x);
		
		var obj={x:3, y:4, callback:function(){alert("hello!!!");}};
		obj.callback(); */
		
		/* var x;
		alert(x==undefined);
		alert("Hello javascript"); */
		
		
		/* alert(a);
		a=1; */
		//var a=1;
		
		/* function f1(){
			//var a=1;
			a=1;
		}
		
		f1();
		alert(a); */
		
		
		/* ----------입출력---------- */
		
		/* var x=3;
		document.write(x); */
		
		
		/* function printResult()
		{
			var btnPrint=document.getElementById("btn-print");
			var x,y;
			x=prompt("x값을 입력하세요",0);
			y=prompt("y값을 입력하세요",0);
			//alert(typeof x);
			x=parseInt(x);
			y=parseInt(y)
			//alert(typeof x);
			//alert(x+y);
			btnPrint.value=x+y;
		} */
		
		//var btnPrint=document.getElementById("btn-print");
		function init(){
			//var btnPrint=document.getElementById("btn-print");
			btnPrint.onclick=function(){
				var x,y;
				x=prompt("x값을 입력하세요",0);
				y=prompt("y값을 입력하세요",0);
				//alert(typeof x);
				x=parseInt(x);
				y=parseInt(y);
				//alert(typeof x);
				//alert(x+y);
				btnPrint.value=x+y;
			};	
		}
		
		
		//window.addEventListener("load", function(){alert("test1")});
		//window.addEventListener("load", function(){alert("test2")});
		window.addEventListener("load", 
				function(){btnPrint=document.getElementById("btn-print");})
		window.onload=init;