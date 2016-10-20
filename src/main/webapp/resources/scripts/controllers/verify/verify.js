'use strict';

angular.module('app').controller('VerifyCtrl', function ($routeParams, LoginService) {
    var ctrl = this;

    ctrl.init = function () {
        LoginService.activate({userKey: $routeParams.userKey}).$promise.then(function (user) {
            console.log(user);
            if(user.id){
                ctrl.success = true;
            } else{
                //User not found.
                ctrl.success = false;
            }
        })
    };

});