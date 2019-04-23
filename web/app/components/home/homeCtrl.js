angular.module('appHomeModule', []).controller('homeCtrl', ['$scope', 'homeContent', function ($scope, homeContent) {
    $scope.dataset = homeContent.getHomeComponentOneContent();
}]);