'use strict';

angular.module('app').factory('MatchService', function ($resource) {
    return $resource('api/match/', {},  {
        completeMatch: {method: 'PUT', url: 'api/match/complete'},
        getMatchHistory: {method: 'GET', url: 'api/match/matchhistory/:username'}
    });
});