'use strict';

angular.module('app').controller('HomeCtrl', function (UserService) {
    var ctrl = this;



    ctrl.init = function () {
        ctrl.welcomeMessage = 'Warcraft II';


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

