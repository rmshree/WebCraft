'use strict';

angular.module('app', ['ngRoute', 'ngResource']).config(function ($routeProvider) {
        $routeProvider
            // after webcraft/#
            .when('/', {
                templateUrl: 'resources/scripts/controllers/landing/landing.html',
                controller: 'LandingCtrl',
                controllerAs: 'ctrl',
                resolve:{}
            }).when('/dummy', {
                templateUrl: 'resources/scripts/controllers/dummy/dummy.html',
                controller: 'DummyCtrl',
                controllerAs: 'ctrl',
                resolve:{}
            }).when('/forums', {
                templateUrl: 'resources/scripts/controllers/forums/forums.html',
                controller: 'ForumsCtrl',
                controllerAs: 'ctrl',
                resolve:{}
            });
    });

angular.module('app').run(function () {

});