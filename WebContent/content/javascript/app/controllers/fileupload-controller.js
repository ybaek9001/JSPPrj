var fileuploadModule = angular.module("fileupload-app", ["ngAnimate"]);

fileuploadModule.controller('fileupload-controller', function ($scope, $http) {
	$scope.dropboxText = "여기에 업로드할 파일을 드래그 하세요";
	
	var dropbox = document.querySelector(".dropbox");
	dropbox.addEventListener("dragenter",function(event){
		$scope.$apply(function(){
			$scope.dropboxText = "오케이~~";
		});
		
		//dropbox.innerText = "오케이";
	});
	

	dropbox.addEventListener("dragleave",function(event){
		$scope.$apply(function(){
			$scope.dropboxText = "여기에 업로드할 파일을 둠~~";
		});
		
		//dropbox.innerText = "오케이";
	});
});