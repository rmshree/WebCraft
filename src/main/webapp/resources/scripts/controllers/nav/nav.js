'use strict';

//$scope angular service-scope
angular.module('app').controller('NavCtrl', function ($scope, $location) {
    var ctrl = this;

    $scope.$watchCollection(function() { return $location.path(); }, function(route) {
        ctrl.inApp = !(route === '/' || route === '/deny');
    });
});

