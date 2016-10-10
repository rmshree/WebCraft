'use strict';

angular.module('app').controller('ProfileCtrl', function (Upload) {
    var ctrl = this;

    ctrl.upload = function (file) {
        var username = 'root';

        // hardcoding the username to root for testing stuff.

        Upload.upload({
            method: 'POST',
            url: 'api/user/' + username + '/upload/avatar',
            data: {
                imageFile: file
            }
        }).success(function() {
            console.log('upload success');
        });

    }
});