var noticesModule = angular.module("notice", []);

noticesModule.controller('noticeController', function ($scope) {
	
	/*var string ="Angular JS Test!!";
	$scope.test = string;*/
    
	var list = [
        { "id": "1", "title": "새로운 서비스가 시작됩니다.", "writer": "newlec", "regdate": "2016-02-16", "hit": 12 },
        { "id": "2", "title": "새로운 강의가 업로드 되었습니다.", "writer": "newlec", "regdate": "2016-02-22", "hit": 72 },
        { "id": "3", "title": "JSP 강의가 업데이트 되었습니다.", "writer": "newlec", "regdate": "2016-02-26", "hit": 245 },
        { "id": "4", "title": "Spring 강의가 새로 추가 되었습니다.", "writer": "newlec", "regdate": "2016-02-27", "hit": 512 },
        { "id": "5", "title": "Javascript 관련 강좌가 추가될 예정입니다.", "writer": "newlec", "regdate": "2016-02-29", "hit": 24 },
        { "id": "6", "title": "jQuery 강좌가 새로 업로드 되었습니다.", "writer": "newlec", "regdate": "2016-03-01", "hit": 123 },
        { "id": "7", "title": "AngularJS 강좌가 추가 되었습니다.", "writer": "newlec", "regdate": "2016-03-03", "hit": 112 },
        { "id": "8", "title": "이벤트가 진행중입니다.", "writer": "newlec", "regdate": "2016-03-10", "hit": 1 },
        { "id": "9", "title": "NodeJS 강의가 진행중입니다.", "writer": "newlec", "regdate": "2016-03-17", "hit": 0 },
        { "id": "10", "title": "Linux 강좌를 확인해 보세요.", "writer": "newlec", "regdate": "2016-03-20", "hit": 0 },
        { "id": "11", "title": "Mean 스택 프로젝트 강좌를 확인해 보세요.", "writer": "newlec", "regdate": "2016-03-30", "hit": 10 },
        { "id": "12", "title": "훈장님 서비스가 시작됩니다.", "writer": "newlec", "regdate": "2016-04-06", "hit": 3 },
        { "id": "13", "title": "훈장님의 지도를 받아보세요.", "writer": "newlec", "regdate": "2016-04-10", "hit": 2 }
    ];

    $scope.list = list;
});