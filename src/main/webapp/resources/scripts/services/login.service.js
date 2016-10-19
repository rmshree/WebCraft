'use strict';

angular.module('app').factory('LoginService', function ($resource) {
    return $resource('api/login/', {},  {
        logInUser: {method: 'PUT', url: 'api/login/web/:username'},
        signOut: {method: 'PUT', url: 'api/login/logout/web/:username'},
        signUp: {method: 'POST', url: 'api/login/signUp'}
    });
});