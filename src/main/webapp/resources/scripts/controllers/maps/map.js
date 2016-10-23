'use strict';

angular.module('app').controller('MapViewCtrl', function ($routeParams, MapService, $http) {
    var ctrl = this;

    ctrl.init = function () {
        MapService.getById({id: $routeParams.id}).$promise.then(function (res) {
            if (res.success) {
                ctrl.map = res.data;
                $http.get(ctrl.map.downloadUrl).then(function (res) {
                    if(res.status === 200){
                        onMapRender(res.data, 'image-canvas');
                    }
                });
            } else {
                ctrl.message = res.message;
            }
        })
    };

    function onMapRender(mapStrings, divId) {

        //read char by char
        var charMap = "";
        var mapString = mapStrings.split("\n");
        var row = mapString[1].split(" ")[1];
        var column = mapString[1].split(" ")[0];
        column = parseInt(column) + 2;
        row = parseInt(row) + 2;

        var dynamicDivWidth = column * 32;
        var divLocation = document.getElementById(divId);
        divLocation.style.width = dynamicDivWidth;
        for (var i = 0; i < row; i++) {
            for (var j = 0; j < column; j++) {
                charMap = mapString[i + 2][j];
                var imageMap = document.createElement('img');
                imageMap.style.display = "inline-block";

                if (charMap === "G") {
                    imageMap.src = 'resources/images/tiles/32/grass.png';
                } else if (charMap === "F") {
                    imageMap.src = 'resources/images/tiles/32/forest.png';
                } else if (charMap === "R") {
                    imageMap.src = 'resources/images/tiles/32/rock.png';
                } else {
                    imageMap.src = 'resources/images/tiles/32/dirt.png';
                }
                divLocation.appendChild(imageMap);
            }
        }
    }

});