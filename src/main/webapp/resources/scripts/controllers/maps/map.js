'use strict';

angular.module('app').controller('MapViewCtrl', function (currentUser, $routeParams, MapService) {
    var ctrl = this;
    ctrl.currentUser = currentUser;

    ctrl.init = function () {
        MapService.getById({id: $routeParams.id}).$promise.then(function (res) {
            if (res.success) {
                ctrl.map = res.data;
                console.log(ctrl.map);
            } else {
                ctrl.message = res.message;
            }
        })
    };

});