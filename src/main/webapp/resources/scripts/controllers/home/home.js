'use strict';

angular.module('app').controller('HomeCtrl', function ($interval, UserService, $location) {
    var ctrl = this;


    ctrl.init = function () {
        getCurrentlyOnline();
        getCurrentlyOnsite();
        $interval(getCurrentlyOnline, 5000);
        $interval(getCurrentlyOnsite, 5000);
    };

    function getCurrentlyOnline() {
        UserService.getOnlineUsers().$promise.then(function (res) {
            ctrl.onlineUsers = res;
        });
    }

    function getCurrentlyOnsite() {
        UserService.getOnsiteUsers().$promise.then(function (res) {
            ctrl.onsiteUsers = res;
        });
    }



    ctrl.goToUserProfile = function (user) {
        $location.path('profile/' + user.username);
    }
});

