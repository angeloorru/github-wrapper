angular.module('myAppHomeCtrl', []).controller('homeCtrl', ['$scope', 'homeContent', function ($scope, homeContent) {
    $scope.dataset = homeContent.getContent();
}]);