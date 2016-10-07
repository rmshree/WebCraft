'use strict';


angular
    .module('app', ['ngMaterial'])
    .controller('NavCtrl', function (UserService, $scope, $location, $timeout, $mdSidenav) {
        $scope.toggleLeft = buildToggler('left');
        $scope.toggleRight = buildToggler('right');

        function buildToggler(componentId) {
            return function() {
                $mdSidenav(componentId).toggle();
            }
        }
    });
