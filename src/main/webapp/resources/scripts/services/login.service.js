'use strict';

angular.module('app').factory('LoginService', function ($resource) {

    return $resource('api/login/', {},  {

        login: {method: 'GET', url: 'api/login/abc/:name'},
        signup: {method: 'GET', url: 'api/user/create/:name'},
        delete: {method: 'GET', url: 'api/user/create/:name'}

    });
});