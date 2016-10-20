'use strict';

angular.module('app').controller('SettingsCtrl', function (currentUser, UserService, $location, Upload) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    console.log(currentUser);
    ctrl.init = function () {
        if(!ctrl.currentUser.id){
            $location.path('/error');
        }
    };

    ctrl.edit = function (user) {
        ctrl.showMessage = false;
        UserService.update(user).$promise.then(function (response) {
            if (response.id) {
                ctrl.currentUser = response;
                ctrl.message = 'Success!';
            }
            else {
                ctrl.message = 'Unexpected error!';
            }
            ctrl.showMessage = true;
        });
    };

    ctrl.upload = function (file) {
        ctrl.uploading = true;
        Upload.upload({
            method: 'POST',
            url: 'api/user/' + ctrl.currentUser.username + '/upload/avatar',
            data: {
                imageFile: file
            }
        }).success(function(response) {
            ctrl.currentUser = response;
            ctrl.uploading = false;
        });
    };

});