'use strict';

angular.module('app').controller('ProfileCtrl', function (UserService, MatchService, currentUser, $routeParams) {
    var ctrl = this;
    ctrl.currentUser = currentUser;

    ctrl.init = function () {
        UserService.getUserByUsername({username: $routeParams.username}).$promise.then(function (user) {
            if(user.id){
                ctrl.profileUser = user;
            } else{
                //User not found.
            }
        });
        MatchService.getMatchHistory({username: $routeParams.username}).$promise.then(function (response) {
            console.log(response);
            if (!response.success) {
                ctrl.matchHistoryFound = false;
                ctrl.message = "Unable to find match history at this moment!"
            }
            else {
                ctrl.matchHistory = [];
                ctrl.matchHistory = response.data;
                if (ctrl.matchHistory.length == 0) {
                    ctrl.matchHistoryFound = false;
                    ctrl.message = "This player has not played any matches yet.!";
                }
                else {
                    ctrl.matchHistoryFound = true;
                }
            }
        });
    };

    ctrl.ConvertDate = function (date) {
        var convertedDate = new Date(date);
        return convertedDate.toLocaleDateString() + " " + convertedDate.toLocaleTimeString();
    };

    ctrl.goToProfile = function (user) {
        window.location.href = "#/profile/" + user;
    }
});