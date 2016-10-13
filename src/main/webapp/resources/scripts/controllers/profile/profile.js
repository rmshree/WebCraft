'use strict';

angular.module('app').controller('ProfileCtrl', function (Upload, currentUser) {
    var ctrl = this;
    ctrl.currentUser = currentUser;

    ctrl.upload = function (file) {
        var username = 'root';
        console.log(file);
        // hardcoding the username to root for testing stuff.

        Upload.upload({
            method: 'POST',
            url: 'api/user/' + username + '/upload/avatar',
            data: {
                imageFile: file
            }
        }).success(function() {
            console.log('upload success');
            window.location.reload();
        });

    }
});