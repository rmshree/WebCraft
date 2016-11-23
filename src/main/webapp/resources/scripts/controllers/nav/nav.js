'use strict';


angular.module('app').controller('NavCtrl', function (LoginService, UserService, $location) {
    var ctrl = this;
    ctrl.currentUser = null;

    ctrl.init = function () {
        UserService.getCurrentUser({apiKey: "Nitta160"}).$promise.then(function (response) {
            if(response.id){
                ctrl.currentUser = response;
            }
        });
    };


    ctrl.goToProfile = function () {
        $location.path('/profile/' + ctrl.currentUser.username);
    };

    ctrl.goToSettings = function () {
        $location.path('/settings/');
    };

    ctrl.goToMessages = function () {
        $location.path('/messages/');
    };


    ctrl.signOut = function () {
        LoginService.signOut({ apiKey: "Nitta160"},ctrl.currentUser.username).$promise.then(function () {
            window.location.reload();
            $location.path('/');
        });

    }

});