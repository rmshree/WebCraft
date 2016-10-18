'use strict';

angular.module('app').controller('HomeCtrl', function (UserService, $interval, $location) {
    var ctrl = this;

    ctrl.init = function () {
        getCurrentlyOnline();
        $interval(getCurrentlyOnline, 10000)
    };

    function getCurrentlyOnline() {
        UserService.getOnsiteUsers().$promise.then(function (res) {
            ctrl.onsiteUsers = res;
        });
    }

    ctrl.goToUserProfile = function (user) {
        $location.path('profile/' + user.username);
    }

});

