'use strict';

angular.module('app').controller('NavCtrl', function (LoginService, UserService, $location) {
    var ctrl = this;
    ctrl.currentUser = null;

    ctrl.init = function () {
        UserService.getCurrentUser().$promise.then(function (response) {
            if(response.id){
                ctrl.currentUser = response;
            }
            console.log(response);
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

    ctrl.goToProfile = function () {
        $location.path('/profile/' + ctrl.currentUser.username);
    };

    ctrl.signOut = function () {
        LoginService.logOutUser(ctrl.currentUser.username).$promise.then(function () {
            window.location.reload();
            window.location.href='/webcraft/#';
        });

    }

});