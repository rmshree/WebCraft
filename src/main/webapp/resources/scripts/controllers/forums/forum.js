'use strict';

angular.module('app').controller('ForumCtrl', function ($route, ForumsService) {
    var ctrl = this;
    var forumsHref = "#/forums";

    ctrl.init = function () {
        ForumsService.getPost({id: $route.current.params.id}).$promise.then(function (response) {
            if(response.id){
                ctrl.post = response;
                ctrl.getCommentsForPost(response);
                ctrl.postFound = true;
            }else{
                ctrl.postFound = false;
            }
        });
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
    };

    //TODO: Only give the user who created the comment the ability to edit the comment.
    ctrl.editComment = function (post, comment) {
        ForumsService.editComment({id: comment.id}, comment.editText).$promise.then(function (response) {
            if (response.id){
                comment.showEditCommentForm = false;
                ctrl.getCommentsForPost(post); //Updates displayed comments.
            }
        })
    };

    //TODO: Only give the user who created the comment the ability to delete the comment.
    ctrl.deleteComment = function(post, comment) {
        ForumsService.deleteComment({id: comment.id}).$promise.then(function (response) {
            if (response !== 0) {
                ctrl.getCommentsForPost(post);
            }
        })
    }

    //TODO: Only give the user who created the post the ability to delete the post.
    ctrl.deletePost = function (post) {
        ForumsService.deletePost({id: post.id}).$promise.then(function (response) {
            if (response !== 0) {
                window.location.href = forumsHref;
            }
        })
    }
});