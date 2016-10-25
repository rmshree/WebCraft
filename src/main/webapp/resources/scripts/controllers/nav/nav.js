'use strict';

angular.module('app').controller('NavCtrl', function (LoginService, UserService, $location) {
    var ctrl = this;
    ctrl.currentUser = null;

    ctrl.init = function () {
        UserService.getCurrentUser().$promise.then(function (response) {
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


    ctrl.signOut = function () {
        LoginService.signOut(ctrl.currentUser.username).$promise.then(function () {
            window.location.reload();
            $location.path('/');
        });

    }

});