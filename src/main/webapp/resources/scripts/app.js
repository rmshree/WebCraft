'use strict';

angular.module('app', ['ngRoute', 'ngResource']).config(function ($routeProvider) {
        $routeProvider
            // after webcraft/#
            .when('/', {
                templateUrl: 'resources/scripts/controllers/landing/landing.html',
                controller: 'LandingCtrl',
                controllerAs: 'ctrl',
                resolve:{
                }
            })
            .when('/home', {
                templateUrl: 'resources/scripts/controllers/home/home.html',
                // controller: 'HomeCtrl',
                // controllerAs: 'ctrl',
                resolve:{}
            })
            .when('/about', {
                templateUrl: 'resources/scripts/controllers/about/about.html',
                // controller: 'AboutCtrl',
                // controllerAs: 'ctrl',
                resolve:{}
            })
            .when('/maps', {
                templateUrl: 'resources/scripts/controllers/maps/maps.html',
                // controller: 'MapsCtrl',
                // controllerAs: 'ctrl',
                resolve:{}
            })
            .when('/rankings', {
                templateUrl: 'resources/scripts/controllers/rankings/rankings.html',
                // controller: 'RankingsCtrl',
                // controllerAs: 'ctrl',
                resolve:{}
            })

            .when('/forums', {
                templateUrl: 'resources/scripts/controllers/forums/forums.html',
                // controller: 'ForumsCtrl',
                // controllerAs: 'ctrl',
                resolve:{}
            })

    });

angular.module('app').run(function () {

});