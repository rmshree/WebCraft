'use strict';

angular.module('app').controller('categoryCtrl', function ($location, ForumsService, UserService, currentUser, Upload) {
    var ctrl = this;

    ctrl.init = function () {
        var category = location.href.split('forums/')[1];
        switch (category){
            case '1': ctrl.category = 'General Discussion';
                break;
            case '2': ctrl.category = 'User Guides';
                break;
            case '3': ctrl.category = 'Technical Support';
                break;
            case '4': ctrl.category = 'Miscellaneous';
                break;
            default: ctrl.category = 'Forum Category'
        }

        ctrl.noPosts = true;

        ForumsService.getCategory({category: category}).$promise.then(function (response) {
            ctrl.posts = response;
            if (ctrl.posts.length == 0)
                ctrl.message = "There are currently no posts.";
        });

       ctrl.currentUser = currentUser;

        ctrl.canPost = false;
        if (typeof(currentUser.id) != "undefined") {
            ctrl.canPost = true;
            /*console.log(currentUser.username + " is logged in.");*/
        }

    };



    ctrl.post = function (newPost) {
        if (newPost.title && newPost.text) {
            newPost.category = location.href.split('forums/')[1];
            newPost.user = ctrl.currentUser;
            ForumsService.add(newPost).$promise.then(function (response) {
                console.log(response);
                if (response.success) {
                    if (newPost.file) {
                        ctrl.uploading = true;
                        Upload.upload({
                            method: 'POST',
                            url: 'api/forums/post/uploadFile/'+response.data.id,
                            data: {
                                file: newPost.file
                            }
                        }).success(function (response) {
                            console.log(response);
                        })
                    }
                    ctrl.showForm = false;
                    ctrl.posts.unshift(response.data);
                    ctrl.noPosts = false;
                }
            });
        }
    };

    ctrl.goToPost = function (post) {
        window.location.href = location.href.split('webcraft/')[1] + '/' + post.id;
    };

});