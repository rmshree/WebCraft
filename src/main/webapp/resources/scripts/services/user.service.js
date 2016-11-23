'use strict';

angular.module('app').factory('UserService', function ($resource) {
    return $resource('api/user/', {},  {
        getUserByUsername: {method: 'GET', url: 'api/user/get/:username/:apiKey'},
        getCurrentUser: {method: 'GET', url: 'api/user/getCurrentUser/:apiKey'},
        getOnsiteUsers: {method: 'GET', isArray:true, url: 'api/user/getOnsiteUsers/:apiKey'},
        getOnlineUsers: {method: 'GET', isArray:true, url: 'api/user/getOnlineUsers/:apiKey'},
        update: {method: 'PUT', url: 'api/user/update/:apiKey'},
        getAllUsers: {method: 'GET', isArray:true, url: 'api/user/getAllUsers/:apiKey'}
    });
});