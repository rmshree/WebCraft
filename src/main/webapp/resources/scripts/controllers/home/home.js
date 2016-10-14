'use strict';

angular.module('app').controller('HomeCtrl', function () {
    var ctrl = this;

    ctrl.info = window.navigator.userAgent;
});