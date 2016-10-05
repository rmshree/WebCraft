'use strict';

angular.module('app').factory('UserService', function ($resource) {

    return $resource('api/user/', {},  {

        getUserByUsername: {method: 'GET', url: 'api/user/get/:username'},
        createNewUser: {method: 'PUT', url: 'api/user/create/:username'}

    });
});