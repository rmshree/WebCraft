'use strict';

angular.module('app').controller('DummyCtrl', function () {
    var ctrl = this;

    ctrl.init = function () {
        console.log('Called from init method on Dummy page.');
    };


});