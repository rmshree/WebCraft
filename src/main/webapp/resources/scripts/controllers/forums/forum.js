'use strict';

angular.module('app').controller('ForumCtrl', function ($route, ForumsService, UserService, Upload) {
    var ctrl = this;
    var forumsHref = "#/forums/";

    ctrl.init = function () {

        ForumsService.getPost({id: $route.current.params.id}).$promise.then(function (response) {
            if(response.id){
                ctrl.post = response;
                ctrl.getCommentsForPost(response);
                ctrl.postFound = true;
                UserService.getCurrentUser().$promise.then(function (response) {
                    ctrl.currentUser = response;
                });
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

    ctrl.addComment = function (post, comment) {
        var newComment = {text: '', user:''};
        newComment.text = comment.text;
        newComment.user = ctrl.currentUser;
        console.log(newComment);
        ForumsService.addComment({id: post.id}, newComment).$promise.then(function (response) {
            console.log(response);
            if(response.success && comment.file){
                Upload.upload({
                    method: 'POST',
                    url: 'api/forums/comment/uploadFile/'+response.data.id,
                    data: {
                        file: comment.file
                    }
                }).success(function (response) {
                    console.log(response);
                });
            }
        });
        location.reload();
    };

    //TODO: Only give the user who created the comment the ability to edit the comment.
    ctrl.editComment = function (comment) {
        console.log(comment);
        ForumsService.editComment({id: comment.id}, comment).$promise.then(function (response) {
            if (response.id){
                console.log(response);

                if (comment.file) {
                    Upload.upload({
                        method: 'POST',
                        url: 'api/forums/comment/uploadFile/'+comment.id,
                        data: {
                            file: comment.file
                        }
                    }).success(function (response) {
                        console.log(response);
                        location.reload();
                    })
                }
                else {
                    comment.showEditCommentForm = false;
                }
            }
        });
    };

    //TODO: Only give the user who created the comment the ability to delete the comment.
    ctrl.deleteComment = function(post, comment) {
        ForumsService.deleteComment({id: comment.id}).$promise.then(function (response) {
            if (response !== 0) {
                ctrl.getCommentsForPost(post);
            }
        })
    };

    //TODO: Only give the user who created the post the ability to delete the post.
    ctrl.deletePost = function (post) {
        ForumsService.deletePost({id: post.id}).$promise.then(function (response) {
            if (response !== 0) {
                var category = location.href.split('forums/')[1];
                window.location.href = forumsHref + category.split('/')[0];
            }
        })
    };

    ctrl.editPost = function (post) {
        console.log(ctrl.edittingPost.title);
        console.log(post);
        ForumsService.editPost({id: post.id}, post).$promise.then(function (response) {
            if (response.id){
                console.log(response);

                if (post.file) {
                    Upload.upload({
                        method: 'POST',
                        url: 'api/forums/post/uploadFile/'+post.id,
                        data: {
                            file: post.file
                        }
                    }).success(function (response) {
                        console.log(response);
                        location.reload();
                    })
                }
                else {
                    ctrl.post.showEditPostForm = false;
                }
            }
        });
    };
    //Jessi: added upload code..not sure if this is right
});