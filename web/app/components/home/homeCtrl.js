angular.module('appHomeModule', []).controller('homeCtrl', ['$scope', 'homeContent', function ($scope, homeContent) {
    /*
    homeContent.getContent().then(function(response){
        $scope.dataset = response;
    });*/
    $scope.dataset = homeContent.getContent();
}]);