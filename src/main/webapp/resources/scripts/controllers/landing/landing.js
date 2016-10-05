'use strict';

angular.module('app').controller('LandingCtrl', function (UserService) {
    var ctrl = this;

    ctrl.init = function () {

        ctrl.welcomeMessage = 'Welcome to Webcraft!';

    };

    ctrl.signUp = function (user) {

        //console.log(user);
        ctrl.StatusMessage = '';
        ctrl.statusFlag = true;

        UserService.getUserByUsername({username: user.username}).$promise.then(function (response) {

            console.log(response);

            //if response is undefined, create a new user
            if (typeof response.id === "undefined") {
                UserService.createNewUser({username: user.username}, user).$promise.then(function (response) {
                    console.log(response);
                    ctrl.statusMessage = 'Sign-up successful!';
                });
            }
            else {
                ctrl.statusFlag = false;
                ctrl.statusMessage = 'Username already exists. Try Again!';
            }

                //console.log("User already exists!");
          });
        };
    });

