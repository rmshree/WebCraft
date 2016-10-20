'use strict';

angular.module('app').controller('SignUpCtrl', function (LoginService) {
    var ctrl = this;

    var EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    ctrl.signUp = function (user) {
        ctrl.statusMessage = '';
        if(testEmail(user.email)){
            LoginService.signUp(user).$promise.then(function (response) {
                if(response.success){
                    ctrl.statusMessage = 'Sign-up successful!  Please check your email for your verification link';
                }else {
                    ctrl.statusMessage = response.message;
                }
            });
        }else{
            ctrl.statusMessage = 'Invalid email address. Please enter a valid email address';
        }

    };

    function testEmail (email) {
        return EMAIL_REGEX.test(email);
    }
});