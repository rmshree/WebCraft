'use strict';

angular.module('app').controller('LandingCtrl', function (UserService) {
    var ctrl = this;

    ctrl.init = function () {

        ctrl.message = 'Welcome to Webcraft';

        //console.log('Called from init method on Landing page.');


    };

    ctrl.signUp = function (user) {
        console.log(user);
        // UserService.create({name: ctrl.name}).$promise.then(function (response) {
        //     console.log(response);
        // })
    };



    /*ctrl.isDisabled = function () {

        if(ctrl.user.name && ctrl.user.username  && ctrl.user.password ){
            return true;
        }
    }
    */

});