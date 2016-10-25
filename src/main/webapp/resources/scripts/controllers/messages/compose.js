'use strict';

angular.module('app').controller('ComposeCtrl', function (UserService, currentUser, MessageService, $routeParams) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.messageTo = null;
    ctrl.message = null;

    ctrl.init = function () {
        UserService.getUserByUsername({username: $routeParams.username}).$promise.then(function (response) {
            if (response.id) {
                ctrl.profileUser = response;
                ctrl.messageTo = ctrl.profileUser.username;
            } else {
                //User not found.
            }
        });
    };

    ctrl.sendMessage = function () {
        console.log(ctrl.messageTo, ctrl.message);
        var message = {
            message_body: ctrl.message,
            sender: ctrl.currentUser,
            receiver: ctrl.profileUser
        }
        MessageService.sendMessage(message).$promise.then(function (response) {
            console.log(response)
        })
    };
    
});