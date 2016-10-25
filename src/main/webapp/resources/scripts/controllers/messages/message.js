'use strict';

angular.module('app').controller('MessagesCtrl', function (UserService, currentUser, MessageService) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.messageTo = null;
    ctrl.message = null;

    ctrl.init = function () {
        UserService.getUserByUsername({username: 'noob'}).$promise.then(function (user) {
            if (user.id) {
                ctrl.profileUser = user;
            } else {
                //User not found.
            }
        })
    };

    ctrl.sendMessage = function () {
        console.log(ctrl.messageTo, ctrl.message);
        var message = {
            id: 1,
            convo_id: 1,
            message_body: ctrl.message,
            sender: ctrl.currentUser,
            receiver: ctrl.profileUser
        }
        MessageService.sendMessage(message).$promise.then(function (response) {
            console.log(response)
        })
    };


});