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

    function onMapRender (mapStrings, divId) {

        //read char by char
        var charMap = "";

        var mapString = mapStrings.split("\n");
        var row = mapString[1].split(" ")[1] + 2;
        var column = mapString[1].split(" ")[0] + 2;

        for (var i = 0; i < row; i++) {
            for (var j = 0; j < column; j++) {
                charMap = mapString[i+2][j];
                var imageMap = document.createElement("img");
                imageMap.style.display = "inline-block";
                var divLocation = document.getElementById("divId");

                if (charMap === "G") {
                    imageMap.src("grass.png");
                } else if (charMap === "F") {
                    imageMap.src("forest.png");
                } else if (charMap === "R") {
                    imageMap.src("rock.png");
                } else {
                    imageMap.src("dirt.png");
                }

                imageMap.id = charMap + "_" + row.toString + "_" + column.toString;
                divLocation.appendChild(imageMap);
                count++;
            }// second for
        }// outer for
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