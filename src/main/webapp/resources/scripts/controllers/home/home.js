'use strict';

angular.module('app').controller('HomeCtrl', function (UserService,$interval) {
    var ctrl = this;



    ctrl.init = function () {
        ctrl.welcomeMessage = 'Warcraft II';
        $interval( function() {
                UserService.getOnsiteUsers().$promise.then(function (res) {
                ctrl.onsiteUsers = res;
            });
            console.log("Called getOnsiteUsers");
        }, 10000)
    };


});

