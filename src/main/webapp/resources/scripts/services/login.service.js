'use strict';

angular.module('app').factory('LoginService', function ($resource) {
    return $resource('api/login/', {},  {
        logInUser: {method: 'PUT', url: 'api/login/:username'},
        logOutUser: {method: 'PUT', url: 'api/login/logout'}

    });
});



