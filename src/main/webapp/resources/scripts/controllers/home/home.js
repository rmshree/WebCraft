'use strict';

angular.module('app').controller('HomeCtrl', function (UserService) {
    var ctrl = this;



    ctrl.init = function () {
        ctrl.welcomeMessage = 'Warcraft II';
        console.log("currently onsite is: " + UserService.getOnsiteUsers());
        ctrl.onsiteUsers = UserService.getOnsiteUsers();

    };


});

