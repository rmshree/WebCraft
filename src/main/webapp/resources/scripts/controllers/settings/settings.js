'use strict';

angular.module('app').controller('SettingsCtrl', function (currentUser, UserService, SettingsService, $location, Upload) {
    var ctrl = this;
    ctrl.currentUser = currentUser;

    ctrl.init = function () {
        if (!ctrl.currentUser.id) {
            ctrl.noUser = true;
        } else {
            getSettings();
            ctrl.showTab('user');
        }
    };

    // Notifications tab
    ctrl.delayUnitOptions = [
        {name: 'Minutes'},
        {name: 'Hours'},
        {name: 'Days'}
    ];

    function getSettings() {
        SettingsService.getSettingsByUser({username: ctrl.currentUser.username}).$promise.then(function (response) {
            if (response.success) {
                ctrl.settings = response.data;
            }
        })
    }

    ctrl.submitSettings = function () {
        ctrl.showNotificationsMessage = false;
        SettingsService.update(ctrl.settings).$promise.then(function (response) {
            if (response.success) {
                ctrl.settings = response.data;
                ctrl.message = 'Success!';
            } else {
                ctrl.message = response.message;
            }
            ctrl.showNotificationsMessage = true;
        })
    };

    // User information tab
    ctrl.edit = function (user) {
        ctrl.showMessage = false;
        UserService.update(user).$promise.then(function (response) {
            if (response.success) {
                ctrl.currentUser = response.data;
                ctrl.message = 'Success!';
            }
            else {
                ctrl.message = response.message;
            }
            ctrl.showUserMessage = true;
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
        }).success(function (response) {
            ctrl.currentUser = response;
            ctrl.uploading = false;
        });
    };

    ctrl.showTab = function (tab) {
        if (tab === 'user') {
            ctrl.showUserTab = true;
            ctrl.showNotificationsTab = false;
        } else {
            ctrl.showUserTab = false;
            ctrl.showNotificationsTab = true;
        }
    };

});