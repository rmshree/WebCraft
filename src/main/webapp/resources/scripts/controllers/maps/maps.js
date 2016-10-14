'use strict';

angular.module('app').controller('MapsCtrl', function (currentUser, MapService, Upload, $sce) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.init = function () {
        console.log(currentUser);

        ctrl.newMap = {};
        MapService.getAll().$promise.then(function (response) {
            ctrl.maps = response;
        })
    };

    ctrl.download = function (map) {
        console.log('Download map', map);
        MapService.download({id: map.id}).$promise.then(function (response) {
            var index = ctrl.maps.indexOf(map);
            ctrl.maps[index] = response;
        });
    };

    ctrl.goToMap = function (map) {
        console.log(map);
    };

    // Util methods
    // ctrl.makePrimaryUrl = function (map) {
    //     if(map.primaryImageFile){
    //         map.primaryImageUrl = $sce.trustAsResourceUrl(window.URL.createObjectURL(map.primaryImageFile));
    //     }
    // };
    //
    // ctrl.makeSecondaryUrl = function (map) {
    //     if(map.secondaryImageFile){
    //         map.secondaryImageUrl = $sce.trustAsResourceUrl(window.URL.createObjectURL(map.secondaryImageFile));
    //     }
    // };

    ctrl.isValid = function () {
        return ctrl.newMap.title && ctrl.newMap.description && ctrl.newMap.file;
    };

    ctrl.uploadMap = function (map) {
        // TODO: need to make sure the title isn't talked already
        var gameMap = {
            title: map.title,
            description: map.description
        };

        MapService.save(gameMap).$promise.then(function (response) {
            console.log(response);
            if(response.id){
                 uploadFile(response, map);
            }
        });
    };

    function uploadFile(res, map) {
        Upload.upload({
            method: 'POST',
            url: 'api/map/' + res.id + '/upload/file',
            data: {
                file: map.file
            }
        }).success(function() {
            console.log('File upload success');
            ctrl.showMapForm = false;
            ctrl.newMap = {};
            ctrl.maps.push(res);
        });
    }

    // function uploadPrimary(id, map) {
    //     Upload.upload({
    //         method: 'POST',
    //         url: 'api/map/' + id + '/upload/primary',
    //         data: {
    //             file: map.primaryImageFile
    //         }
    //     }).success(function() {
    //         console.log('Primary upload success');
    //     });
    // }
    //
    // function uploadSecondary(id, map) {
    //     Upload.upload({
    //         method: 'POST',
    //         url: 'api/map/' + id + '/upload/secondary',
    //         data: {
    //             file: map.secondaryImageFile
    //         }
    //     }).success(function() {
    //         console.log('Secondary upload success');
    //     });
    // }

});