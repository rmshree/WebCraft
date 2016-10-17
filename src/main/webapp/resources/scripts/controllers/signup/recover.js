'use strict';

angular.module('app').controller('RecoverCtrl', function (UserService) {
    var ctrl = this;

    ctrl.init = function ()
    {
        ctrl.welcomeMessage = 'Welcome to NittaCraft';
        ctrl.welcomeMessage2 = 'a.out Edition';
    };

    ctrl.Email = function (user) {
        ctrl.StatusMessage = '';
        ctrl.statusFlag = true;

        UserService.passwordRecovery({email: user.email}).$promise.then(function (response) {
//this is where I need to check if the email is associated with an account or not*****

            //if response is undefined, create a new user
            if (response.id) {
                EmailService.sendPassswordRecoveryEmail({username: user.username}, user).$promise.then(function (response) {
                    ctrl.statusMessage = 'Check your email for a verification link';
                });
            }
            else {
                ctrl.statusFlag = false;
                ctrl.statusMessage = 'Email ' + user.email + ' is not associated with an account';
            }
        });

//         UserService.getUserByEmail({email: user.email}).$promise.then(function (response) {
// //this is where I need to check if the email is associated with an account or not*****
//
//             //if response is undefined, create a new user
//             if (response.id) {
//                 EmailService.sendPassswordRecoveryEmail({username: user.username}, user).$promise.then(function (response) {
//                     ctrl.statusMessage = 'Check your email for a verification link';
//                 });
//             }
//             else {
//                 ctrl.statusFlag = false;
//                 ctrl.statusMessage = 'Email ' + user.email + ' is not associated with an account';
//             }
//         });
    };






//         UserService.getUserByEmail({email: user.email}).$promise.then(function (response) {
// //this is where I need to check if the email is associated with an account or not*****
//
//             //if response is undefined, create a new user
//             if (response.id) {
//                 EmailService.sendPassswordRecoveryEmail({username: user.username}, user).$promise.then(function (response) {
//                     ctrl.statusMessage = 'Check your email for a verification link';
//                 });
//             }
//             else {
//                 ctrl.statusFlag = false;
//                 ctrl.statusMessage = 'Email ' + user.email + ' is not associated with an account';
//             }
//         });
//     };

    // ctrl.login = function (user) {
    //
    //     ctrl.statusMessage ='';
    //     ctrl.statusFlag = false;
    //
    //     LoginService.logInUser({username: user.username}, user.password).$promise.then(function(response) {
    //
    //         if (response.id) {
    //             ctrl.statusFlag = true;
    //             ctrl.statusMessage = "You're logged in!";
    //             ctrl.currentUser = response;
    //             window.location.reload();
    //         }
    //         else {
    //             ctrl.statusMessage = "Could not log in...";
    //             console.log(response);
    //         }
    //     });
    //
    // };
});

