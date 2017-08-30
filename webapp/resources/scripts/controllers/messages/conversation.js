'use strict';

angular.module('app').controller('ConversationCtrl', function (currentUser, MessageService, $routeParams, $interval, $route) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.messages = [];
    const ONE_SECOND = 1000;
    var timer = null;

    ctrl.init = function () {
        MessageService.getConversationById({id: $routeParams.id}).$promise.then(function (response) {
            if (response.id) {
                ctrl.conversation = response;
            } else {
                ctrl.conversationNotFound = true;
            }
        });
        getMessages();
        timer = $interval(getMessages, ONE_SECOND * 30);

    };

    function getMessages() {
        if ($route.current.$$route.controller !== 'ConversationCtrl') {
            $interval.cancel(timer);
        } else {
            MessageService.getMessagesByConversationId({id: $routeParams.id}).$promise.then(function (response) {
                ctrl.messages = response;
            });
        }
    }

    ctrl.sendMessage = function (messageBody) {
        if (ctrl.messages[0].receiver.username === ctrl.currentUser.username) {
            ctrl.messageTo = ctrl.messages[0].sender;
        } else {
            ctrl.messageTo = ctrl.messages[0].receiver;
        }
        var message = {
            message_body: messageBody,
            sender: ctrl.currentUser,
            receiver: ctrl.messageTo
        };

        MessageService.sendMessage(message).$promise.then(function (response) {
            if (response.id) {
                ctrl.messages.push(response);
                ctrl.message = null;
            } else {
                // error occured
            }
        });
    };

});