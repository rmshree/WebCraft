'use strict';

angular.module('app').controller('RecoverCtrl', function (LoginService) {
    var ctrl = this;

    ctrl.submit = function (user) {
        LoginService.recovery(user.email).$promise.then(function (response) {
            console.log(response);
        });
    };

});

