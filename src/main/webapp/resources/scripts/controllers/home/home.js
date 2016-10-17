'use strict';

angular.module('app').controller('HomeCtrl', function (UserService, $interval) {
    var ctrl = this;



    ctrl.init = function () {
        ctrl.welcomeMessage = 'Warcraft II';

        $interval( function() {
            ctrl.onsiteUsers = UserService.getOnsiteUsers();
            console.log("interval call");
        }, 10000);

    };


});

