'use strict';

angular.module('app').controller('ForumsCtrl', function ($location, ForumsService) {
    var ctrl = this;

    ctrl.init = function () {
        ForumsService.getAll().$promise.then(function (response) {
            ctrl.posts = response;
        });
    };

    ctrl.post = function (newPost) {
        if (newPost.title && newPost.text) {
            ForumsService.add(newPost).$promise.then(function (response) {
                console.log(response);
                if (response.id) {
                    ctrl.showForm = false;
                    ctrl.posts.push(response);
                }
            });
        }
    };

    ctrl.goToPost = function (post) {
        $location.path('forum/' + post.id);
    };

});