'use strict';

angular.module('app').controller('LandingCtrl', function (UserService) {
    var ctrl = this;



    ctrl.init = function () {
        ctrl.welcomeMessage = 'NITTACRAFT';
        ctrl.welcomeMessage2 = 'a.out Edition';
    };



    ctrl.signUp = function (user) {
        ctrl.StatusMessage = '';
        ctrl.statusFlag = true;

        UserService.getUserByUsername({username: user.username}).$promise.then(function (response) {

            //if response is undefined, create a new user
            if (!response.id) {
                UserService.createNewUser({username: user.username}, user).$promise.then(function (response) {
                    ctrl.statusMessage = 'Sign-up successful!';
                });
            }
            else {
                ctrl.statusFlag = false;
                ctrl.statusMessage = 'Username ' + user.username + ' exists already';
            }
        });
    };
});

