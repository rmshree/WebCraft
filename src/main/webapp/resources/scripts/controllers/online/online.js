'use strict';

angular.module('app').controller('OnlineCtrl', function ($interval, UserService, $location, $route) {
    var ctrl = this;
    var onlineInterval = null;

    ctrl.init = function () {
        getOnlineOnSiteUsers();
        onlineInterval = $interval(getOnlineOnSiteUsers, 5000);
    };

    function getOnlineOnSiteUsers() {
        if ($route.current.$$route.controller !== 'OnlineCtrl') {
            $interval.cancel(onlineInterval);
        } else {
            UserService.getOnlineUsers({ apiKey: "Nitta160"}).$promise.then(function (res) {
                ctrl.onlineUsers = res;
            });
            UserService.getOnsiteUsers({ apiKey: "Nitta160"}).$promise.then(function (res) {
                ctrl.onsiteUsers = res;
            });
        }
    }

    ctrl.goToUserProfile = function (user) {
        $location.path('profile/' + user.username);
    }
});

