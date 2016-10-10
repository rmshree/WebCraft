'use strict';

angular.module('app', ['ngRoute', 'ngResource']).config(function ($routeProvider) {
    $routeProvider
    // after webcraft/#
      /*  .when('/', {
       /!*     templateUrl: 'resources/scripts/controllers/landing/landing.html',
            controller: 'LandingCtrl',
            controllerAs: 'ctrl',
            resolve: {}
            
         /!*   templateUrl: 'resources/scripts/controllers/home/home.html',
             controller: 'HomeCtrl',
             controllerAs: 'ctrl',
             resolve: {}*!/!*!/
        })*/
        .when('/', {
            templateUrl: 'resources/scripts/controllers/landing/landing.html',
            controller: 'LandingCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/about', {
            templateUrl: 'resources/scripts/controllers/about/about.html',
            controller: 'AboutCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/maps', {
            templateUrl: 'resources/scripts/controllers/maps/maps.html',
            controller: 'MapsCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/rankings', {
            templateUrl: 'resources/scripts/controllers/rankings/rankings.html',
            controller: 'RankingsCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/forums', {
            templateUrl: 'resources/scripts/controllers/forums/forums.html',
            controller: 'ForumsCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/forum/:id', {
            templateUrl: 'resources/scripts/controllers/forums/forum.html',
            controller: 'ForumCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/profile', {
            templateUrl: 'resources/scripts/controllers/profile/profile.html',
            controller: 'ProfileCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/error', {
            templateUrl: 'resources/scripts/controllers/error/error.html'
        }).otherwise({
        redirectTo: '/error'
    });
});

angular.module('app').run(function () {

});