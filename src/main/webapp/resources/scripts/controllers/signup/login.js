'use strict';

angular.module('app').controller('LoginCtrl', function (UserService, LoginService, $location) {
    var ctrl = this;
    ctrl.loginUser = {};
    ctrl.signupUser = {};
    ctrl.buttonDisabled = false;

    ctrl.login = function (loginUser) {
        ctrl.message = '';
        LoginService.logInUser({username: loginUser.username, apiKey: "Nitta160"}, loginUser.password).$promise.then(function (response) {
            if(response.success){
                window.location.reload();
                $location.path('/');
            } else{
               ctrl.message = response.message;
            }
        });

    };

    var EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    ctrl.signUp = function (user) {
        ctrl.statusMessage = '';
        ctrl.buttonDisabled = true;
        if(testEmail(user.email)){
            LoginService.signUp({ apiKey: "Nitta160"}, user).$promise.then(function (response) {
                if(response.success){
                    ctrl.statusMessage = 'Sign-up successful!  Please check your email for your verification link';
                }else {
                    ctrl.statusMessage = response.message;
                    ctrl.buttonDisabled = false;
                }
            });
        }else{
            ctrl.statusMessage = 'Invalid email address. Please enter a valid email address';
            ctrl.buttonDisabled = false;
        }
    };

    function testEmail (email) {
        return EMAIL_REGEX.test(email);
    }



});

