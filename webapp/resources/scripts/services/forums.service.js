'use strict';

angular.module('app').factory('ForumsService', function ($resource) {
    return $resource('api/forums/', {},  {
        getPost: {method: 'GET', url: 'api/forums/get/:id'},
        getAll: {method: 'GET', url: 'api/forums/all', isArray: true},
        getCategory:{method: 'GET', url: 'api/forums/post/category/:category', isArray: true},
        add: {method: 'PUT', url: 'api/forums/add'},
        getCommentsForPost: {method: 'GET', url: 'api/forums/:id/comments', isArray: true},
        addComment: {method: 'PUT', url: 'api/forums/:id/add/comment'},
        editComment: {method: 'POST', url: 'api/forums/comment/edit/:id'},
        editPost: {method: 'POST', url: 'api/forums/post/edit/:id'},
        deleteComment: {method: 'DELETE', url: 'api/forums/comment/delete/:id'},
        deletePost: {method: 'DELETE', url:'api/forums/post/delete/:id'},
    });
});