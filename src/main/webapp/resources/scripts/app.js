'use strict';

angular.module('app', ['ngRoute', 'ngResource']).config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'resources/scripts/controllers/landing/landing.html',
                controller: 'LandingCtrl',
                controllerAs: 'ctrl',
                resolve:{}
            }).when('/forums', {
                templateUrl: 'resources/scripts/controllers/forums/forums.html',
                controller: 'ForumsCtrl',
                controllerAs: 'ctrl',
                resolve:{}
            }).when('/deny', {
                templateUrl: 'resources/scripts/controllers/deny/deny.html'
            }).otherwise({
                redirectTo: '/deny'
            });
    });

angular.module('app').run(function () {

});