'use strict';

angular.module('app').controller('SignupCtrl', function (UserService, LoginService) {
    var ctrl = this;

    ctrl.init = function () {
        ctrl.showLogin = true;
        ctrl.showSignUp = false;
    };

    ctrl.signUp = function (user) {

        UserService.getUserByUsername({username: user.username}).$promise.then(function (response) {
            //if response is undefined, create a new user
            if (!response.id) {
                LoginService.signUp(user).$promise.then(function (response) {
                    if(response.id){
                        ctrl.signUpMessage = 'Please check your e-mail to verify your account';
                    }
                });
            }
            else {
                ctrl.signUpMessage = 'Username ' + user.username + ' exists already';
            }
        });
    };

    ctrl.login = function (user) {

        LoginService.logInUser({username: user.username}, user.password).$promise.then(function (response) {

            if (response.id) {
                ctrl.currentUser = response;
                window.location.reload();
            }
            else {
                ctrl.logInMessage = "Could not log in...";
                console.log(response);
            }
        });

    };
});

