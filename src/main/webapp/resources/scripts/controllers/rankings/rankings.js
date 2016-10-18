'use strict';

angular.module('app').controller('RankingsCtrl', function ($interval, UserService, $location) {
    var ctrl = this;

    ctrl.init = function () {
        getCurrentlyOnline();
        $interval(getCurrentlyOnline, 10000)
    };

    function getCurrentlyOnline() {
        UserService.getOnsiteUsers().$promise.then(function (res) {
            ctrl.onlineUsers = res;
        });
    }

    ctrl.goToUserProfile = function (user) {
        $location.path('profile/' + user.username);
    }
});