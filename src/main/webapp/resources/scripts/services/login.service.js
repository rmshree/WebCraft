'use strict';

angular.module('app').factory('LoginService', function ($resource) {
    return $resource('api/login/', {},  {
        logInUser: {method: 'GET', url: ' api/login/:username/:password'}

    });
});