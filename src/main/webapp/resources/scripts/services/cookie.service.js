'use strict';

angular.module('app').factory('CookieService', function ($resource) {
    return $resource('api/user/', {},  {
        getCurrentUser: {method: 'GET', url: 'api/user/getCurrentUser/:username'}
    });
});