'use strict';

angular.module('app').controller('ForumsCtrl', function ($location, ForumsService) {
    var ctrl = this;

    ctrl.init = function () {
        ForumsService.getAll().$promise.then(function (response) {
            ctrl.posts = response;
            console.log(response);
        });
    };

    ctrl.post = function () {

        var post = {
            title: 'Test',
            text: 'lled any time the html is rendered by the browser '
        };

        ForumsService.add(post).$promise.then(function (response) {
            console.log(response);
        });
    };
});