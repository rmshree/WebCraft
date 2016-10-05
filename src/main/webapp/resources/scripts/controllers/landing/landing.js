'use strict';

angular.module('app').controller('LandingCtrl', function (UserService) {
    var ctrl = this;

    ctrl.init = function () {

        ctrl.message = 'Welcome to Webcraft';
        ctrl.message2 = 'a.out Edition';

    };

    ctrl.signUp = function (user) {
        console.log(user);

        UserService.getUserByUsername({username: user.username}).$promise.then(function (response) {

            console.log(response);

            //if response is null, create a new user
            if (typeof response.id === "undefined") {
                UserService.createNewUser({username: user.username}, user).$promise.then(function (response) {
                    console.log(response);
                    document.getElementById("signUpMessage").innerHTML = "Sign-up succesful!"
                });
            }//end if
            else
            {
                document.getElementById("signUpMessage").innerHTML = 'Username "' + user.username + '" exists already'
                //console.log("User already exists!");
            }

        });
        };
    });

