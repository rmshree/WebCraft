'use strict';

angular.module('app').controller('LandingCtrl', function (UserService) {
    var ctrl = this;

    ctrl.init = function () {
        // making backend call

        ctrl.name = 'Nitta';

        console.log('Called from init method on Landing page.');
        ctrl.message = 'Welcome to Nittacraft a.out Edition';

    };

    ctrl.printName = function (name) {
        console.log(name);
        // UserService.create({name: ctrl.name}).$promise.then(function (response) {
        //     console.log(response);
        // })
    };

    ctrl.clickSecondB = function () {
        console.log(ctrl.date);
        // var date = new Date();
        // console.log(date);
    };

    ctrl.isDisabled = function () {

        if(ctrl.name){
            return true;
        }
    }

});