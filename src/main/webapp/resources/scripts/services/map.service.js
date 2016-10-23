'use strict';

angular.module('app').factory('MapService', function ($resource) {
    return $resource('api/map/', {},  {
        getAll: {method: 'GET', url: 'api/map/all', isArray: true},
        save: {method: 'PUT', url: 'api/map/save'},
        download: {method: 'GET', url: 'api/map/download/:id'},
        getById: {method: 'GET', url: 'api/map/:id'}
    });
});