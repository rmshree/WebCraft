'use strict';

angular.module('app').controller('ConversationCtrl', function (UserService, currentUser, MessageService, $routeParams) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.messages = [];

    ctrl.init = function () {
        MessageService.getConversationById({id:$routeParams.id}).$promise.then(function (response) {
            ctrl.messages = response;
            console.log(response);
        });
    };

    ctrl.sendMessage = function () {
        console.log(ctrl.messageTo, ctrl.message);
        var message = {
            message_body: ctrl.message,
            sender: ctrl.currentUser,
            receiver: ctrl.profileUser
        };
        MessageService.sendMessage(message).$promise.then(function (response) {
            console.log(response)
        })
    };
    
});