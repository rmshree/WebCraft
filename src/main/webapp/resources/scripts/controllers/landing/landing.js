'use strict';

angular.module('app').controller('LandingCtrl', function (UserService) {
    var ctrl = this;

    ctrl.init = function () {

        ctrl.message = 'Welcome to Webcraft';

    };

    ctrl.signUp = function (user) {
        console.log(user);

        UserService.getUserByUsername({username: user.username}).$promise.then(function (response) {

            console.log(response);

            //if response is null, create a new user
            if (typeof response.id === "undefined") {
                UserService.createNewUser({username: user.username}, user).$promise.then(function (response) {
                    console.log(response);
                });
            }//end if
            else console.log("User already exists!");
          });
        };
    });

