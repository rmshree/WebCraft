'use strict';

angular.module('app').controller('NavCtrl', function (UserService) {
    var ctrl = this;

    ctrl.login = function (username, password) {
        console.log(username, password);

    };
});