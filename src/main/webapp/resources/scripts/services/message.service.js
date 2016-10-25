'use strict';

angular.module('app').factory('MessageService', function ($resource) {
    return $resource('api/messages/', {}, {
        sendMessage: {method: 'POST', url: 'api/messages/save'}
    });
});