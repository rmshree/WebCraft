'use strict';

angular.module('app').controller('MessagesCtrl', function (currentUser, MessageService, UserService) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.toUser = null;
    ctrl.isSendingMessage = false;
    ctrl.statusMessage = '';

    ctrl.init = function () {
        ctrl.getMessages();
    };

    ctrl.getMessages = function () {
        MessageService.getMessages({username:ctrl.currentUser.username}).$promise.then(function (response) {
            console.log(response);
            ctrl.messages = response;
        })
    };

    ctrl.sendMessage = function(newMessage) {

        UserService.getUserByUsername(newMessage.receiver).$promise.then(function (response) {
            if (response.id) {
                ctrl.toUser = response;
                var message = {
                    message_body: newMessage.body,
                    sender: ctrl.currentUser,
                    receiver: ctrl.toUser
                }

                console.log(message);
                MessageService.sendMessage(message).$promise.then(function (response) {
                    if (response.id) {
                        ctrl.isSendingMessage = false;
                        ctrl.messages.push(response); //SHOULD REMOVE THIS LATER
                        console.log(response);
                    }
                });
            }
            else {
                //receiver does not exist
                ctrl.statusMessage = "User" + newMessage.receiver + " does not exits.";
            }
        });
    };


});