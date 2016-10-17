'use strict';

angular.module('app').controller('HomeCtrl', function (UserService) {
    var ctrl = this;



    ctrl.init = function () {
        ctrl.welcomeMessage = 'Warcraft II';
        UserService.getOnsiteUsers().$promise.then(function (res) {
            ctrl.onsiteUsers = res;
        });
    };


});

