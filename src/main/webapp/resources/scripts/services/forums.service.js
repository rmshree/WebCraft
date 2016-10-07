'use strict';

angular.module('app').factory('ForumsService', function ($resource) {
    return $resource('api/user/', {},  {
        getAll: {method: 'GET', url: 'api/forums/all', isArray: true},
        add: {method: 'PUT', url: 'api/forums/add'},
        getCommentsForPost: {method: 'GET', url: 'api/forums/:id/comments', isArray: true},
        addComment: {method: 'PUT', url: 'api/forums/:id/add/comment'}
    });
});