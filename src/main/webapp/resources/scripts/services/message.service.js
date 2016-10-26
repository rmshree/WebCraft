'use strict';

angular.module('app').factory('MessageService', function ($resource) {
    return $resource('api/messages/', {}, {
        sendMessage: {method: 'POST', url: 'api/messages/save'},
        getMessages: {method: 'GET', url: 'api/messages/user/:username', isArray: true},
        getConversations: {method: 'GET', url: 'api/messages/get/conversations/:username', isArray: true},
        getMessagesByConversationId: {method: 'GET', url: 'api/messages/get/messages/:id', isArray: true}
    });
});