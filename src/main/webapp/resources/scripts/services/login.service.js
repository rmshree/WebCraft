'use strict';

angular.module('app').factory('LoginService', function ($resource) {
    return $resource('api/login/', {},  {

    });
});



