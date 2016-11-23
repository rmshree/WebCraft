'use strict';

angular.module('app').factory('LoginService', function ($resource) {
    return $resource('api/login/', {},  {
        logInUser: {method: 'PUT', url: 'api/login/web/:username/:apiKey'},
        signOut: {method: 'PUT', url: 'api/login/logout/web/:apiKey'},
        signUp: {method: 'POST', url: 'api/login/signUp/:apiKey'},
        recovery: {method: 'PUT', url: 'api/login/passwordRecovery/:apiKey'},
        activate: {method: 'GET', url: 'api/login/activate/:userKey/:apiKey'}
    });
});