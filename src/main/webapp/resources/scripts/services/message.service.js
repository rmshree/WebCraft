'use strict';

angular.module('app').factory('MessageService', function ($resource) {
    return $resource('api/messages/', {}, {
        sendMessage: {method: 'POST', url: 'api/messages/save/:apiKey'},
        getMessages: {method: 'GET', url: 'api/messages/user/:username/:apiKey', isArray: true},
        getConversations: {method: 'GET', url: 'api/messages/get/conversations/:username/:apiKey', isArray: true},
        getConversationById: {method: 'GET', url: 'api/messages/get/conversation/:id/:apiKey'},
        getMessagesByConversationId: {method: 'GET', url: 'api/messages/get/messages/:id/:apiKey', isArray: true}
    });
});