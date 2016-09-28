'use strict';

angular.module('app').controller('LandingCtrl', function () {
    var ctrl = this;

    ctrl.init = function () {
        console.log('Called from init method on Landing page.');
        ctrl.message = 'Welcome to Webcraft';
    };


});