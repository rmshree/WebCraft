'use strict';

angular.module('app').controller('MapsCtrl', function (currentUser, MapService, Upload, $sce, $scope) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.newMap = {};

    $scope.$watchCollection('ctrl.newMap.file', function(file){
        console.log(file);
        if(file !== undefined){
            var reader = new FileReader();
            reader.onload = function(){
                var dataURL = reader.result;
                console.log(dataURL);
                onMapRender(dataURL, 'imgDiv');
            };
            var blob = file.slice(0, file.size);

            reader.readAsBinaryString(blob);
        }

    });

    ctrl.init = function () {
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
        }).success(function(response) {
            console.log('File upload success', response);
            ctrl.showMapForm = false;
            ctrl.newMap = {};
            ctrl.maps.push(response);
        });
    }

    function onMapRender (mapStrings, divId) {

        //read char by char
        var charMap = "";

        var mapString = mapStrings.split("\n");
        var row = mapString[1].split(" ")[1].charCodeAt(0) + 2;
        var column = mapString[1].split(" ")[0].charCodeAt(0) + 2;

        for (var i = 0; i < row; i++) {
            for (var j = 0; j < column; j++) {
                charMap = mapString[i+2][j];
                var imageMap = document.createElement('img');
                imageMap.style.display = "inline-block";
                var divLocation = document.getElementById(divId);

                if (charMap === "G") {
                    imageMap.src = 'resources/images/tiles/grass.png';
                } else if (charMap === "F") {
                    imageMap.src = 'resources/images/tiles/forest.png';
                } else if (charMap === "R") {
                    imageMap.src = 'resources/images/tiles/rock.png';
                } else {
                    imageMap.src = 'resources/images/tiles/dirt.png';
                }

                imageMap.id = charMap + "_" + row.toString + "_" + column.toString;
                divLocation.appendChild(imageMap);
            }// second for
        }// outer for
    }

});