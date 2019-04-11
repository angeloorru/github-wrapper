angular.module('indexModule', []).controller('indexController', ['$scope', function ($scope) {
    $scope.date = new Date();
}]);