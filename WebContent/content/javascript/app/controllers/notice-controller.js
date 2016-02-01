var noticeModule = angular.module("notice", []);

noticeModule.controller('notice-controller', function ($scope, $http) {
    	
	var test="hello world";
	$scope.aa=test;
	
	/*var list = [
        { "id": "1", "title": "새로운 서비스가 시작됩니다.", "writer": "newlec", "regdate": "2016-02-16", "hit": 12, "img":"icon-recommend.png" },
        { "id": "2", "title": "새로운 강의가 업로드 되었습니다.", "writer": "newlec", "regdate": "2016-02-22", "hit": 72, "img":"icon-recommend.png" },
        { "id": "3", "title": "JSP 강의가 업데이트 되었습니다.", "writer": "newlec", "regdate": "2016-02-26", "hit": 245, "img":"icon-recommend.png" },
        { "id": "4", "title": "Spring 강의가 새로 추가 되었습니다.", "writer": "newlec", "regdate": "2016-02-27", "hit": 512, "img":"icon-recommend.png" },
        { "id": "5", "title": "Javascript 관련 강좌가 추가될 예정입니다.", "writer": "newlec", "regdate": "2016-02-29", "hit": 24, "img":"icon-recommend.png" },
        { "id": "6", "title": "jQuery 강좌가 새로 업로드 되었습니다.", "writer": "newlec", "regdate": "2016-03-01", "hit": 123, "img":"icon-recommend.png" },
        { "id": "7", "title": "AngularJS 강좌가 추가 되었습니다.", "writer": "newlec", "regdate": "2016-03-03", "hit": 112, "img":"icon-recommend.png" },
        { "id": "8", "title": "이벤트가 진행중입니다.", "writer": "newlec", "regdate": "2016-03-10", "hit": 1, "img":"icon-recommend.png" },
        { "id": "9", "title": "NodeJS 강의가 진행중입니다.", "writer": "newlec", "regdate": "2016-03-17", "hit": 0, "img":"icon-recommend.png" },
        { "id": "10", "title": "Linux 강좌를 확인해 보세요.", "writer": "newlec", "regdate": "2016-03-20", "hit": 0, "img":"icon-recommend.png" },
        { "id": "11", "title": "Mean 스택 프로젝트 강좌를 확인해 보세요.", "writer": "newlec", "regdate": "2016-03-30", "hit": 10, "img":"icon-recommend.png" },
        { "id": "12", "title": "훈장님 서비스가 시작됩니다.", "writer": "newlec", "regdate": "2016-04-06", "hit": 3, "img":"icon-recommend.png" },
        { "id": "13", "title": "훈장님의 지도를 받아보세요.", "writer": "newlec", "regdate": "2016-04-10", "hit": 2, "img":"icon-recommend.png" }
    ];*/
	
    //VM 초기 설정
	$scope.list = null; 
	$scope.noticeRegVisible = false;
	
	$scope.title="";
	$scope.content="";
	
	$scope.noticeRegButtonText="글작성?";
	
	//Simple GET request example
	$http({
		method : 'GET',
		url : '../../../../customer/noticeJSON?p=1'
	}).then(function successCallback(response) {
		$scope.list = response.data;
		for(var i=0; i<$scope.list.length; i++)
			$scope.list[i].isChecked=false;
		
	}, function errorCallback(response) {
		alert("실패");
	});
	
	
	
	
    
    // 이벤트 설정
    $scope.toggleNoticeReg = function(){
    	$scope.noticeRegVisible=!$scope.noticeRegVisible;	
    	if($scope.noticeRegVisible)
    		$scope.noticeRegButtonText = "글쓰기 숨기기?"
    	else
    		$scope.noticeRegButtonText="글작성할까?";
    };
    
    $scope.btnWriteClick = function(){
    	var notice = {"title": $scope.title, "content":$scope.content};
    	
    	$http({
    		method : 'POST',
    		url : '../../../../customer/noticeRegAjax',
    		data : notice
    		//header: {'Content-Type':'applicaton/json; charset=utf-8'},	
    	}).then(function successCallback(response) {
    		if(response.data == "ok")
    	    	$scope.list.splice(0,0,notice);
    			$scope.title="";
    			$scope.content="";    
    	}, function errorCallback(response) {
    		alert("실패");
    	});
    	event.preventDefault();    	
    }
    
    $scope.btnDelClicked = function(){
    	var cnt=0;
    	for(var i=0; i<$scope.list.length; i++)
    		if($scope.list[i].isChecked)
    			cnt++;
    	alert(cnt);
    }
    
});