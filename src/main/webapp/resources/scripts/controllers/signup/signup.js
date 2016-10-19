'use strict';

angular.module('app').controller('SignUpCtrl', function (UserService, LoginService) {
    var ctrl = this;

    ctrl.init = function () {
        ctrl.welcomeMessage = 'Welcome to NittaCraft';
        ctrl.welcomeMessage2 = 'a.out Edition';
    };

    ctrl.signUp = function (user) {
        ctrl.StatusMessage = '';
        ctrl.statusFlag = true;
        // EmailService.sendEmail().$promise.then(function (response) {
        //     console.log(response);
        // });
        UserService.getUserByUsername({username: user.username}).$promise.then(function (response) {
            //if response is undefined, create a new user
            if (!response.id) {
                ctrl.disabledButton = true;
                LoginService.signUp({username: user.username}, user).$promise.then(function (response) {
                    ctrl.statusMessage = 'Sign-up successful!  Please check your email for your verification link';
                });
            }
            else {
                ctrl.statusFlag = false;
                ctrl.statusMessage = 'Username ' + user.username + ' exists already';
            }
        });
    };

    ctrl.login = function (user) {

        ctrl.statusMessage ='';
        ctrl.statusFlag = false;

        LoginService.logInUser({username: user.username}, user.password).$promise.then(function(response) {

            if (response.id) {
                ctrl.statusFlag = true;
                ctrl.statusMessage = "You're logged in!";
                ctrl.currentUser = response;
                window.location.reload();
            }
            else {
                ctrl.statusMessage = "Could not log in...";
                console.log(response);
            }
        });

    };
});

