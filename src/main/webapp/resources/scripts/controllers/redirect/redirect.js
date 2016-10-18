angular.module('app').controller('downloadCtrl',['deviceDetector',function(deviceDetector) {
    var ctrl = this;
    ctrl.data = deviceDetector;
    ctrl.allData = JSON.stringify(ctrl.data, null, 2);

}])
