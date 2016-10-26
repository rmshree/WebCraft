'use strict';

angular.module('app').controller('RankingsCtrl', function (UserService) {
    var ctrl = this;

    ctrl.init = function () {
        UserService.getAllUsers().$promise.then(function (response) {
            ctrl.allUsers = response;
        });
    };



    ctrl.goToUserProfile = function (user) {
        $location.path('profile/' + user.username);
    };

    ctrl.winPercentage = function (user) {
        return ((user.win / (user.win + user.loss)) * 100).toFixed(2);
    };

    // ctrl.goToPost = function (post) {
    //     $location.path('forum/' + post.id);
    // };

});



// getAllUsers: {method: 'GET', isArray:true, url: 'api/user/getAllUsers'}
