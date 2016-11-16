'use strict';

angular.module('app').controller('MapsCtrl', function (currentUser, MapService, Upload, $sce, $scope, $location) {
    var ctrl = this;
    ctrl.currentUser = currentUser;
    ctrl.newMap = {};

    function hasExtension(inputID, exts) {
        var fileName = document.getElementById(inputID).value;
        return (new RegExp('(' + exts.join('|').replace(/\./g, '\\.') + ')$')).test(fileName);
    }

    $scope.$watchCollection('ctrl.newMap.file', function (file) {
        if (file !== undefined) {
            var reader = new FileReader();
            reader.onload = function () {
                var dataURL = reader.result;
                onMapRender(dataURL, 'image-canvas');
            };
            var blob = file.slice(0, file.size);
            reader.readAsBinaryString(blob);
        }
    });

    ctrl.goToMap = function (map) {
        $location.path('map/' + map.id);
    };

    ctrl.init = function () {
        MapService.getAll().$promise.then(function (response) {
            ctrl.maps = response;
        })
    };

    ctrl.uploadMap = function (map) {
        if (ctrl.currentUser) {
            var gameMap = {
                title: map.title,
                numberOfPlayers: map.numberOfPlayers,
                user: ctrl.currentUser
            };
            MapService.save(gameMap).$promise.then(function (response) {
                console.log(response);
                if (response.success) {
                    uploadFile(response.data, map);
                } else {
                    ctrl.message = response.message;
                }
            });
        }
    };

    ctrl.download = function (map) {
        MapService.download({id: map.id}).$promise.then(function (response) {
            if (response.success) {
                var index = ctrl.maps.indexOf(map);
                ctrl.maps[index] = response.data;
            } else {
                // should never happen
                console.log(response.message);
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
        }).success(function (response) {
            if (response.id) {
                html2canvas([document.getElementById('image-canvas')], {
                    onrendered: function (canvas) {
                        var data = canvas.toDataURL('image/png');
                        var file = dataURLtoBlob(data);
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
        }).success(function (map) {
            if (map.id) {
                ctrl.showMapForm = false;
                ctrl.newMap = {};
                ctrl.maps.push(map);
            }
        });
    }

    function onMapRender(mapStrings, divId) {

        //read char by char
        var charMap = "";

        var mapString = mapStrings.split("\n");
        var row = mapString[1].split(" ")[1];
        var column = mapString[1].split(" ")[0];
        column = parseInt(column) + 2;
        row = parseInt(row) + 2;

        // miss-match in the smallmap and bigmap txt file format
        var numPlayers = mapString[row + 3];
        if (numPlayers.length > 3) {
            // for big map
            numPlayers = mapString[row + 2];
        }
        ctrl.newMap.numberOfPlayers = parseInt(numPlayers);
        ctrl.newMap.title = mapString[0];
        var dynamicDivWidth = column * 15;
        var divLocation = document.getElementById(divId);
        divLocation.style.width = dynamicDivWidth;
        for (var i = 0; i < row; i++) {
            for (var j = 0; j < column; j++) {
                charMap = mapString[i + 2][j];
                var imageMap = document.createElement('img');
                imageMap.style.display = "inline-block";

                if (charMap === "G") {
                    imageMap.src = 'resources/images/tiles/15/grass.png';
                } else if (charMap === "F") {
                    imageMap.src = 'resources/images/tiles/15/forest.png';
                } else if (charMap === "R") {
                    imageMap.src = 'resources/images/tiles/15/rock.png';
                } else {
                    imageMap.src = 'resources/images/tiles/15/dirt.png';
                }
                divLocation.appendChild(imageMap);
            }
        }
    }

    function dataURLtoBlob(dataURL) {
        // Decode the dataURL
        var binary = atob(dataURL.split(',')[1]);
        // Create 8-bit unsigned array
        var array = [];
        for (var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        // Return our Blob object
        return new Blob([new Uint8Array(array)], {type: 'image/png'});
    }

});