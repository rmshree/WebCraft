'use strict';

angular.module('app').controller('ProfileCtrl', function (Upload, UserService, currentUser, $routeParams) {
    var ctrl = this;
    ctrl.currentUser = currentUser;

    ctrl.init = function () {
        UserService.getUserByUsername({username: $routeParams.username}).$promise.then(function (user) {
            if(user.id){
                ctrl.profileUser = user;
            } else{
                //User not found.
            }
        })
    };

    ctrl.upload = function (file) {
        if(ctrl.currentUser.id === ctrl.profileUser.id){
            ctrl.uploading = true;

            Upload.upload({
                method: 'POST',
                url: 'api/user/' + ctrl.currentUser.username + '/upload/avatar',
                data: {
                    imageFile: file
                }
            }).success(function(response) {
                ctrl.profileUser = response;
                ctrl.currentUser = response;
                ctrl.uploading = false;
            });
        }

    }
});