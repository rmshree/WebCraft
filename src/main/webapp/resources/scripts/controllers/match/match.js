'use strict';

angular.module('app').controller('MatchCtrl', function (MatchService) {
    var ctrl = this;

    ctrl.init = function () {
        ctrl.matchContainer = {winners:[],losers:[]};
    };

    ctrl.saveMatch = function (matchContainer) {
        console.log(matchContainer);
        MatchService.completeMatch({apiKey: "aaaaa"}, matchContainer).$promise.then(function (response) {
            console.log(response);
        });
    };

});