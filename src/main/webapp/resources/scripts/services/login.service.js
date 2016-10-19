'use strict';

angular.module('app').factory('LoginService', function ($resource) {
    return $resource('api/login/', {},  {
        logInUser: {method: 'PUT', url: 'api/login/web/:username'},
        signOut: {method: 'PUT', url: 'api/login/logout/web'},
        signUp: {method: 'POST', url: 'api/login/signUp'},
        recovery: {method: 'PUT', url: 'api/login/passwordRecovery'}

    });
});