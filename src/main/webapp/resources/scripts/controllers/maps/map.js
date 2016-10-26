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

        //set-up and initialize the bitmap
        var topLeft = ""; //the tile on the upper left
        var topMid = ""; //the tile directly above
        var topRight = ""; //the tile on the upper right
        var midLeft = ""; //the tile on the left
        var charMap = ""; //the tile we want to place
        var midRight = ""; //the tile on the right
        var bottomLeft = ""; //the tile on the lower left
        var bottomMid = ""; //the tile directly below
        var bottomRight = ""; //the tile on the lower right

        var mapString = mapStrings.split("\n");
        var row = mapString[1].split(" ")[1];
        var column = mapString[1].split(" ")[0];
        column = parseInt(column) + 2;
        row = parseInt(row) + 2;

        var dynamicDivWidth = column * 32;
        var divLocation = document.getElementById(divId);
        divLocation.style.width = dynamicDivWidth;

        for (var i = 2; i < row; i++) {
            for (var j = 0; j < column; j++) {
                //initialize the bitmap
                topLeft = mapString[i-1][j-1];
                topMid = mapString[i-1][j];
                topRight = mapString[i-1][j+1];
                midLeft = mapString[i][j-1];
                charMap = mapString[i][j];
                midRight = mapString[i][j+1];
                bottomLeft = mapString[i+1][j-1];
                bottomMid = mapString[i+1][j];
                bottomRight = mapString[i+1][j+1];

                var imageMap = document.createElement('img');
                imageMap.style.display = "inline-block";

                if (charMap === "D") {
                    //for top-left corner
                    if ((topLeft !== charMap) && (topMid !== charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtTopLeft.png';
                    }
                    //for top-mid border
                    else if ((topMid !== charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtTopMid.png';
                    }
                    //for top-right corner
                    else if ((topMid !== charMap) && (topRight !== charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtTopRight.png';
                    }
                    //for right-side border
                    else if ((topLeft === charMap) && (topMid === charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtRightBorder.png';
                    }
                    //for middle
                    else if ((topLeft === charMap) && (topRight === charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomLeft === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtMid.png';
                    }
                    //for left-side border
                    else if ((topMid === charMap) && (topRight === charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtLeftBorder.png';
                    }
                    //for bottom-left corner
                    else if ((topMid === charMap) && (topRight === charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomLeft !== charMap) && (bottomMid !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtBottomLeft.png';
                    }
                    //for bottom-mid border
                    else if ((topLeft === charMap) && (topMid === charMap) && (topRight === charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomMid !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtBottomMid.png';
                    }
                    //for bottom-right corner
                    else if ((topLeft === charMap) && (topMid === charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomMid !== charMap) && (bottomRight !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/dirtBottomRight.png';
                    }
                }

                else if (charMap === "F") {
                    //for top-left corner
                    if ((topLeft !== charMap) && (topMid !== charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeTopLeft.png';
                    }
                    //for top-mid border
                    else if ((topMid !== charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeTopMid.png';
                    }
                    //for top-right corner
                    else if ((topMid !== charMap) && (topRight !== charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeTopRight.png';
                    }
                    //for right-side border
                    else if ((topLeft === charMap) && (topMid === charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeRightBorder.png';
                    }
                    //for middle
                    else if ((topLeft === charMap) && (topRight === charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomLeft === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeMid.png';
                    }
                    //for left-side border
                    else if ((topMid === charMap) && (topRight === charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeLeftBorder.png';
                    }
                    //for bottom-left corner
                    else if ((topMid === charMap) && (topRight === charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomLeft !== charMap) && (bottomMid !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeBottomLeft.png';
                    }
                    //for bottom-mid border
                    else if ((topLeft === charMap) && (topMid === charMap) && (topRight === charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomMid !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeBottomMid.png';
                    }
                    //for bottom-right corner
                    else if ((topLeft === charMap) && (topMid === charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomMid !== charMap) && (bottomRight !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/treeBottomRight.png';
                    }
                }

                else if (charMap === "R") {
                    //for top-left corner
                    if ((topLeft !== charMap) && (topMid !== charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockTopLeft.png';
                    }
                    //for top-mid border
                    else if ((topMid !== charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockTopMid.png';
                    }
                    //for top-right corner
                    else if ((topMid !== charMap) && (topRight !== charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockTopRight.png';
                    }
                    //for right-side border
                    else if ((topLeft === charMap) && (topMid === charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockRightBorder.png';
                    }
                    //for middle
                    else if ((topLeft === charMap) && (topRight === charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomLeft === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockMid.png';
                    }
                    //for left-side border
                    else if ((topMid === charMap) && (topRight === charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockLeftBorder.png';
                    }
                    //for bottom-left corner
                    else if ((topMid === charMap) && (topRight === charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomLeft !== charMap) && (bottomMid !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockBottomLeft.png';
                    }
                    //for bottom-mid border
                    else if ((topLeft === charMap) && (topMid === charMap) && (topRight === charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomMid !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockBottomMid.png';
                    }
                    //for bottom-right corner
                    else if ((topLeft === charMap) && (topMid === charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomMid !== charMap) && (bottomRight !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/rockBottomRight.png';
                    }
                }

                else if (charMap === "W") {
                    //for top-left corner
                    if ((topLeft !== charMap) && (topMid !== charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterTopLeft.png';
                    }
                    //for top-mid border
                    else if ((topMid !== charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterTopMid.png';
                    }
                    //for top-right corner
                    else if ((topMid !== charMap) && (topRight !== charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterTopRight.png';
                    }
                    //for right-side border
                    else if ((topLeft === charMap) && (topMid === charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomLeft === charMap) && (bottomMid === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterRightBorder.png';
                    }
                    //for middle
                    else if ((topLeft === charMap) && (topRight === charMap) &&
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomLeft === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterMid.png';
                    }
                    //for left-side border
                    else if ((topMid === charMap) && (topRight === charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomMid === charMap) && (bottomRight === charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterLeftBorder.png';
                    }
                    //for bottom-left corner
                    else if ((topMid === charMap) && (topRight === charMap) &&
                        (midLeft !== charMap) && (midRight === charMap) &&
                        (bottomLeft !== charMap) && (bottomMid !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterBottomLeft.png';
                    }
                    //for bottom-mid border
                    else if ((topLeft === charMap) && (topMid === charMap) && (topRight === charMap)
                        (midLeft === charMap) && (midRight === charMap) &&
                        (bottomMid !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterBottomMid.png';
                    }
                    //for bottom-right corner
                    else if ((topLeft === charMap) && (topMid === charMap) &&
                        (midLeft === charMap) && (midRight !== charMap) &&
                        (bottomMid !== charMap) && (bottomRight !== charMap)
                    ) {
                        imageMap.src = 'resources/images/tiles/32/waterBottomRight.png';
                    }
                }

                //There is only one grass tile used in rendering
                else {
                    imageMap.src = 'resources/images/tiles/32/grass.png';
                }
                divLocation.appendChild(imageMap);
            }
        }
    }

});