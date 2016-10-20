'use strict';

angular.module('app').controller('ProfileCtrl', function (UserService, currentUser, $routeParams) {
    var ctrl = this;
    ctrl.currentUser = currentUser;

    ctrl.init = function () {
        UserService.getUserByUsername({username: $routeParams.username}).$promise.then(function (user) {
            if(user.id){
                ctrl.profileUser = user;
            } else{
                //User not found.
            }
        })
    };

});