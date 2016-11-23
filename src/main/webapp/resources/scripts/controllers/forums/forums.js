'use strict';

angular.module('app').controller('forumsCtrl', function ($location, ForumsService) {
    var ctrl = this;

    ctrl.init = function () {
        ctrl.GeneralDiscussion = 1;
        ctrl.UserGuides = 2;
        ctrl.TechnicalSupport = 3;
        ctrl.Miscellaneous = 4;
    };

    ctrl.goToForum = function (category) {
        window.location.href = "#/forums/" + category;
    };
});