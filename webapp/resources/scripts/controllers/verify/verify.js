'use strict';

angular.module('app').controller('VerifyCtrl', function ($routeParams, LoginService, $location) {
    var ctrl = this;

    ctrl.init = function () {
        LoginService.activate({userKey: $routeParams.userKey}).$promise.then(function (response) {
            if(response.success){
                $location.path('/login');
            } else{
                ctrl.message = response.message;
            }
        })
    };

});