'use strict';

angular.module('app').controller('ForumsCtrl', function (ForumsService) {
    var ctrl = this;

    ctrl.init = function () {
        ForumsService.getAll().$promise.then(function (response) {
            ctrl.posts = response;
            console.log('All forums', response);
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

    ctrl.getCommentsForPost = function (post) {
        post.comments = [];
        ForumsService.getCommentsForPost({id: post.id}).$promise.then(function (response) {
            post.comments = response;
        })
    };

    ctrl.addComment = function (post, text) {
        ForumsService.addComment({id: post.id}, text).$promise.then(function (response) {
            if(response.id){
                post.showCommentForm = false;
                post.comments.push(response);
            }
        })
    }
});