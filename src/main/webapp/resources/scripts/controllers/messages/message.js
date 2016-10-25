'use strict';

angular.module('app').controller('MessagesCtrl', function (currentUser, MessageService) {
    var ctrl = this;
    ctrl.currentUser = currentUser;

    ctrl.init = function () {
        ctrl.getMessages();
    };

    ctrl.getMessages = function () {
        MessageService.getMessages({username:ctrl.currentUser.username}).$promise.then(function (response) {
            console.log(response);
            ctrl.messages = response;
        })
    };


});