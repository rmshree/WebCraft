'use strict';

angular.module('app').controller('ForumsCtrl', function ($location) {
    var ctrl = this;

    ctrl.init = function () {
        console.log('This is called any time the html is rendered by the browser ' +
            'because in the html, I have \'ng-init="ctrl.init()"\' which is this function.');
    };

    ctrl.goToLanding = function () {
        // $location is a service that's provided by Angular for route and location related stuff.
        // check out it's doc if you're interested. https://docs.angularjs.org/api/ng/service/$location
        $location.path('/');
    }

});