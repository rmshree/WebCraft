'use strict';

angular.module('app').controller('RecoverCtrl', function (LoginService) {
    var ctrl = this;

    var EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    ctrl.submit = function (user) {
        if(testEmail(user.email)){
            LoginService.recovery(user.email).$promise.then(function (response) {
                console.log(response);
                ctrl.message = response.message;
            });
        }else{
            ctrl.message = 'Please enter a valid email address';
        }
    };

    function testEmail (email) {
        return EMAIL_REGEX.test(email);
    }
});

