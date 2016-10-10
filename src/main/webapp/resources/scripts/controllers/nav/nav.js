'use strict';

angular.module('app').controller('NavCtrl', function (UserService, CookieService) {
    var ctrl = this;

    ctrl.login = function (username, password) {
        console.log(username, password);

        //Create a cookie
        UserService.getUserByUsername({username: username}).$promise.then(function (response) {
            CookieService.getCurrentUser({username: username});
            console.log(response);
        });


    };
});