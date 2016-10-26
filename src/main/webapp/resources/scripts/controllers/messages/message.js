'use strict';

angular.module('app').controller('MessagesCtrl', function (currentUser, $route, MessageService, UserService, $location) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.isSendingMessage = false;
    ctrl.statusMessage = '';

    ctrl.init = function () {
        MessageService.getConversations({username: ctrl.currentUser.username}).$promise.then(function (response) {
            ctrl.conversations = response;
        })
    };

    ctrl.goToConversation = function (id) {
        $location.path('/conversation/' + id);
    };

    ctrl.sendMessage = function (newMessage) {

        UserService.getUserByUsername({username: newMessage.receiver}).$promise.then(function (response) {
            if (response.id) {
                var message = {
                    message_body: newMessage.body,
                    sender: ctrl.currentUser,
                    receiver: response
                };

                MessageService.sendMessage(message).$promise.then(function (response) {
                    if (response.id) {
                        ctrl.isSendingMessage = false;
                        $route.reload();
                    }
                });
            }
            else {
                ctrl.statusMessage = "User " + newMessage.receiver + " does not exits.";
            }
        });
    };


});