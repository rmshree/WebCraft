'use strict';

angular.module('app').controller('NavCtrl', function (UserService, $scope, $location)
{
    var ctrl = this;
    ctrl.init = function ()
    {}

    $scope.$watchCollection(function() { return $location.path(); }, function(route)
    {
        ctrl.inApp = !(route === '/' || route === '/deny');
    });

});