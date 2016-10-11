'use strict';

angular.module('app').controller('NavCtrl', function (LoginService) {
    var ctrl = this;

    ctrl.login = function (user) {

        ctrl.statusMessage ='';
        ctrl.statusFlag = false;

        LoginService.logInUser({username: user.username}, user.password).$promise.then(function(response) {

            if (response.id) {
                ctrl.statusFlag = true;
                ctrl.statusMessage = "You're logged in!";
                console.log(response);
            }
            else {
                ctrl.statusMessage = "Could not log in...";
                console.log(response);
            }
        });

    };

});