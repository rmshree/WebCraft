'use strict';

angular.module('app').controller('downloadCtrl', function(deviceDetector) {
    var ctrl = this;

    ctrl.data = deviceDetector;

    ctrl.getUrl = function () {
        console.log('resources/images/' + ctrl.data.os + '.png');
        return 'resources/images/' + ctrl.data.os + '.png';
    };

});




