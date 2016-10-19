'use strict';

angular.module('app').controller('downloadCtrl', function(deviceDetector) {
    var ctrl = this;

    ctrl.data = deviceDetector;

});




