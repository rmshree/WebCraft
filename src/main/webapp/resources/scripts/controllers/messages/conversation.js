'use strict';

angular.module('app').controller('ConversationCtrl', function (UserService, currentUser, MessageService, $routeParams) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.messageTo = null;
    ctrl.message = null;

    ctrl.init = function () {
        // UserService.getUserByUsername({username: $routeParams.username}).$promise.then(function (response) {
        //     if (response.id) {
        //         ctrl.profileUser = response;
        //         ctrl.messageTo = ctrl.profileUser.username;
        //     } else {
        //         //User not found.
        //     }
        // });

        MessageService.getConversationById({id:$routeParams.id}).$promise.then(function (response) {
            console.log(response);


            ctrl.messages = response;

        });


        console.log($routeParams.id);
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