'use strict';

angular.module('app').controller('LandingCtrl', function (UserService) {
    var ctrl = this;

    ctrl.init = function () {

        ctrl.message = 'Welcome to Webcraft';

    };

    ctrl.signUp = function (user) {
        console.log(user);
        UserService.createNewUser({username: user.username}, user).$promise.then(function (response) {
            console.log(response);
        });
        UserService.getUserByUsername({username: user.username}).$promise.then(function (response) {
            console.log(response)
        });
    };

});