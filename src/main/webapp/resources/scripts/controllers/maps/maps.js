'use strict';

angular.module('app').controller('MapsCtrl', function (currentUser, MapService, Upload, $sce, $scope) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.newMap = {};

    $scope.$watchCollection('ctrl.newMap.file', function(file){
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

    ctrl.uploadMap = function (map) {
        // TODO: need to make sure the title isn't talked already
        var gameMap = {
            title: map.title,
            description: map.description,
            user: ctrl.currentUser
        };

        MapService.save(gameMap).$promise.then(function (response) {
            console.log(response);
            if(response.id){
                uploadFile(response, map);
            }
        });
    };

    ctrl.download = function (map) {
        MapService.download({id: map.id}).$promise.then(function (response) {
            var index = ctrl.maps.indexOf(map);
            ctrl.maps[index] = response;
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
            if(response.id){
                html2canvas([document.getElementById('imgDiv')], {
                    onrendered: function (canvas) {
                        var data = canvas.toDataURL('image/png');
                        var file= dataURLtoBlob(data);
                        uploadImage(response.id, file);
                    }
                });
            }
        });
    }

    ctrl.isValid = function () {
        return ctrl.newMap.title && ctrl.newMap.file;
    };

    function uploadImage(id, img) {
        Upload.upload({
            method: 'POST',
            url: 'api/map/' + id + '/upload/primary',
            data: {
                file: img
            }
        }).success(function(map) {
            if(map.id){
                ctrl.showMapForm = false;
                ctrl.newMap = {};
                ctrl.maps.push(map);
            }
        });
    }

    function onMapRender (mapStrings, divId) {

        //read char by char
        var charMap = "";

        var mapString = mapStrings.split("\n");
        var row = mapString[1].split(" ")[1];
        var column = mapString[1].split(" ")[0];
        column = parseInt(column) + 2;
        row = parseInt(row) + 2;
        ctrl.newMap.title = mapString[0];
        var dynamicDivWidth = column * 15;

        for (var i = 0; i < row; i++) {
            for (var j = 0; j < column; j++) {
                charMap = mapString[i+2][j];
                var imageMap = document.createElement('img');
                imageMap.style.display = "inline-block";
                var divLocation = document.getElementById(divId);
                divLocation.style.width = dynamicDivWidth;

                if (charMap === "G") {
                    imageMap.src = 'resources/images/tiles/grass.png';
                    //potentially add imageMap.style.width="35%"???
                } else if (charMap === "F") {
                    imageMap.src = 'resources/images/tiles/forest.png';
                } else if (charMap === "R") {
                    imageMap.src = 'resources/images/tiles/rock.png';
                } else {
                    imageMap.src = 'resources/images/tiles/dirt.png';
                }

                imageMap.id = charMap + "_" + row.toString + "_" + column.toString;
                divLocation.appendChild(imageMap);
            }
        }
    }

    function dataURLtoBlob(dataURL) {
        // Decode the dataURL
        var binary = atob(dataURL.split(',')[1]);
        // Create 8-bit unsigned array
        var array = [];
        for(var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        // Return our Blob object
        return new Blob([new Uint8Array(array)], {type: 'image/png'});
    }

});