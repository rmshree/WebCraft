'use strict';

angular.module('app').factory('ForumsService', function ($resource) {
    return $resource('api/forums/', {},  {
        getPost: {method: 'GET', url: 'api/forums/get/:id/:apiKey'},
        getAll: {method: 'GET', url: 'api/forums/all/:apiKey', isArray: true},
        getCategory:{method: 'GET', url: 'api/forums/post/category/:category/:apiKey', isArray: true},
        add: {method: 'PUT', url: 'api/forums/add/:apiKey'},
        getCommentsForPost: {method: 'GET', url: 'api/forums/:id/comments/:apiKey', isArray: true},
        addComment: {method: 'PUT', url: 'api/forums/:id/add/comment/:apiKey'},
        editComment: {method: 'POST', url: 'api/forums/comment/edit/:id/:apiKey'},
        editPost: {method: 'POST', url: 'api/forums/post/edit/:id/:apiKey'},
        deleteComment: {method: 'DELETE', url: 'api/forums/comment/delete/:id/:apiKey'},
        deletePost: {method: 'DELETE', url:'api/forums/post/delete/:id/:apiKey'},
    });
});