'use strict';

angular.module('app').factory('MapService', function ($resource) {
    return $resource('api/map/', {},  {
        getAll: {method: 'GET', url: 'api/map/all/:apiKey', isArray: true},
        save: {method: 'PUT', url: 'api/map/save/:apiKey'},
        download: {method: 'GET', url: 'api/map/download/:id/:apiKey'},
        getById: {method: 'GET', url: 'api/map/:id/:apiKey'}
    });
});