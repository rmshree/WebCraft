'use strict';

angular.module('app').controller('LoginCtrl', function (UserService, LoginService, $location) {
    var ctrl = this;

    ctrl.login = function (user) {
        LoginService.logInUser({username: user.username}, user.password).$promise.then(function (response) {
            if(response.success){
                window.location.reload();
                $location.path('/');
            } else{
               ctrl.message = response.message;
            }
        });

    };
});

