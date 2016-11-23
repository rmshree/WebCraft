'use strict';

angular.module('app').factory('SettingsService', function ($resource) {
    return $resource('api/settings/', {},  {
        getSettingsByUser: {method: 'GET', url: 'api/settings/user/:username/:apiKey'},
        update: {method: 'PUT', url: 'api/settings/update/:apiKey'}
    });
});