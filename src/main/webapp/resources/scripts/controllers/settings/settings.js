/**
 * Created by DGM on 10/14/16.
 */
'use strict';

angular.module('app').controller('SettingsCtrl', function (UserService) {
    var ctrl = this;


    //Happens first even vefore hitting submit to page
    ctrl.init = function ()
    {

        //grabbing the user from backend and saves locally to
        //if not
        ctrl.user = UserService.getCurrentUser();
        ctrl.welcomeMessage = 'Welcome to NittaCraft';
        ctrl.welcomeMessage2 = 'a.out Edition';
    };


    //Calle isn serbvices.js
    //.edit matches my settings html
    //user is my paramaet from  html
    ctrl.edit = function (user)
    {
        ctrl.StatusMessage = '';
        ctrl.statusFlag = true;

        /* userservce is found in fonrt end userservice which then calls the backed end cotnroller and they tke care */
        //* Parameters = A and B,  A= path varibale from backend must match this is visible, B=my local variabl from front ent not visble   *//


        UserService.editUser({username: user.username}, user).$promise.then(function (response)
        {
            if (response.id)
            {
                //When succesful
                ctrl.statusMessage = 'Username ' + user.username + ' exists already';
            }
            else
            {
                //Unsuccesful
                ctrl.statusFlag = false;
                ctrl.statusMessage = 'Username ' + user.username + ' exists already';
            }
        });
    };
});