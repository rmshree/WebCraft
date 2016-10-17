'use strict';

angular.module('app').factory('UserService', function ($resource) {
    return $resource('api/user/', {},  {
        getUserByUsername: {method: 'GET', url: 'api/user/get/:username'},
        create: {method: 'PUT', url: 'api/user/create/:username'},
        getCurrentUser: {method: 'GET', url: 'api/user/getCurrentUser'},
        getUserByEmail: {method: 'GET', url: 'api/user/getByEmail/:email'},
        passwordRecovery: {method: 'GET', url: 'api/user/passwordRecovery/:email'}




    });
});