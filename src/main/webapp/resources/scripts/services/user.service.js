'use strict';

angular.module('app').factory('UserService', function ($resource) {

    return $resource('api/user/', {},  {

        create: {method: 'GET', url: 'api/user/create/:name'}

    });
});