'use strict';

angular.module('app', ['ngRoute', 'ngResource', 'ngFileUpload', 'ng.deviceDetector']).config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'resources/scripts/controllers/home/home.html',
            controller: 'HomeCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/login', {
            templateUrl: 'resources/scripts/controllers/signup/login.html',
            controller: 'LoginCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })

        .when('/signup', {
            templateUrl: 'resources/scripts/controllers/signup/signup.html',
            controller: 'SignUpCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })

        .when('/recover', {
            templateUrl: 'resources/scripts/controllers/signup/recover.html',
            controller: 'RecoverCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })

        .when('/about', {
            templateUrl: 'resources/scripts/controllers/about/about.html',
            controller: 'AboutCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/messages', {
            templateUrl: 'resources/scripts/controllers/messages/message.html',
            controller: 'MessagesCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/maps', {
            templateUrl: 'resources/scripts/controllers/maps/maps.html',
            controller: 'MapsCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/online', {
            templateUrl: 'resources/scripts/controllers/online/online.html',
            controller: 'OnlineCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/rankings', {
            templateUrl: 'resources/scripts/controllers/rankings/rankings.html',
            controller: 'RankingsCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/forums/', {
            templateUrl: 'resources/scripts/controllers/forums/forums.html',
            controller: 'forumsCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/forums/:category', {
            templateUrl: 'resources/scripts/controllers/forums/category.html',
            controller: 'categoryCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/forums/:id/:id', {
            templateUrl: 'resources/scripts/controllers/forums/post.html',
            controller: 'postCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/download', {
            templateUrl: 'resources/scripts/controllers/download/download.html',
            controller: 'downloadCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })
        .when('/redirect', {
            templateUrl: 'resources/scripts/controllers/redirect/redirect.html',
            controller: 'downloadCtrl',
            controllerAs: 'ctrl',
            resolve: {}
        })

        .when('/settings', {
            templateUrl: 'resources/scripts/controllers/settings/settings.html',
            controller: 'SettingsCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/conversation/:id', {
            templateUrl: 'resources/scripts/controllers/messages/conversation.html',
            controller: 'ConversationCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/messages', {
            templateUrl: 'resources/scripts/controllers/messages/message.html',
            controller: 'MessagesCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })

        .when('/verify/:userKey', {
            templateUrl: 'resources/scripts/controllers/verify/verify.html',
            controller: 'VerifyCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/map/:id', {
            templateUrl: 'resources/scripts/controllers/maps/map.html',
            controller: 'MapViewCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/profile/:username', {
            templateUrl: 'resources/scripts/controllers/profile/profile.html',
            controller: 'ProfileCtrl',
            controllerAs: 'ctrl',
            resolve: {
                currentUser: function (UserService) {
                    return UserService.getCurrentUser().$promise;
                }
            }
        })
        .when('/match', {
            templateUrl: 'resources/scripts/controllers/match/match.html',
            controller: 'MatchCtrl',
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