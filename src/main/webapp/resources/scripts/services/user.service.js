'use strict';

angular.module('app').factory('UserService', function ($resource) {
    return $resource('api/user/', {},  {
        getUserByUsername: {method: 'GET', url: 'api/user/get/:username'},
        getCurrentUser: {method: 'GET', url: 'api/user/getCurrentUser'},
        getOnsiteUsers: {method: 'GET', isArray:true, url: 'api/user/getOnsiteUsers'}


    });
});