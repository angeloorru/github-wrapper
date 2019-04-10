angular.module('myIndexController', []).controller('indexController', ['$scope', function ($scope) {
    $scope.date = new Date();
}]);