'use strict';

angular.module('app').controller('RecoverCtrl', function (LoginService) {
    var ctrl = this;
    ctrl.buttonDisabled = false;
    var EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    ctrl.submit = function (user) {
        ctrl.buttonDisabled = true;
        if(testEmail(user.email)){
            LoginService.recovery({ apiKey: "Nitta160"}, user.email).$promise.then(function (response) {
                console.log(response);
                ctrl.message = response.message;
            });
        }else{
            ctrl.message = 'Please enter a valid email address';
            ctrl.buttonDisabled = false;
        }
    };

    function testEmail (email) {
        return EMAIL_REGEX.test(email);
    }
});

