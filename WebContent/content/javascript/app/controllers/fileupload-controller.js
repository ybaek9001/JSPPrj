var fileuploadModule = angular.module("fileupload-app", ["ngAnimate"]);

fileuploadModule.controller('fileupload-controller', function ($scope, $http) {
	$scope.dropboxClass="";
	$scope.dropboxText = "여기에 업로드할 파일을 드래그 하세요";
	
	/*var a = "a"; //자바스크립트는 undefined, null, "" 은 false로 인식
	var a1 = null;
	var a2 = 'hello';
	
	var b = a&&a1&&a2;
	
	alert(b);
	
	if(!a){
		alert("ok")
	};
	if(!!a)
		alert("sorry");
	*/
	
	var dropbox = document.querySelector(".dropbox");
	dropbox.addEventListener("dragenter",function(event){
		event.stopPropagation();
		event.preventDefault();
		
		$scope.$apply(function(){
			$scope.dropboxText = "오케이~~";
		});	
		//dropbox.innerText = "오케이";
	});
	

	dropbox.addEventListener("dragleave",function(event){
		event.stopPropagation();
		event.preventDefault();
		
		$scope.$apply(function(){
			$scope.dropboxText = "여기에 업로드할 파일을 둠~~";
			$scope.dropboxClass="";
		});
		
		//dropbox.innerText = "오케이";
	});
	
	dropbox.addEventListener("dragover",function(event){
		event.stopPropagation();
		event.preventDefault();
		
		var valid = event.dataTransfer 
					&& event.dataTransfer.types 
					&& event.dataTransfer.types.indexOf("Files")>=0; //  >0 는 조건은 있으면이란 의미
		$scope.$apply(function(){
			//$scope.dropboxText = event.dataTransfer.types;
			$scope.dropboxText = valid? "드롭하세요!": "유효한 파일이 아닙니다~~";
			$scope.dropboxClass = valid? "valid":"invalid";		
		});
		//dropbox.innerText = "오케이";
	});
	
	dropbox.addEventListener("drop",function(event){
		event.stopPropagation();
		event.preventDefault();
		
		$scope.$apply(function(){
			$scope.files = event.dataTransfer.files;	
		})
		
		//alert(files.length)
		var str ="";
		for(var i=0;i<files.length;i++)
			alert(files[i].name);
	});	
});