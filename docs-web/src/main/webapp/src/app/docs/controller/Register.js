'use strict';

/**
 * Register controller.
 */
angular.module('docs').controller('Register', function(Restangular, $scope, $rootScope, $state, $stateParams, $dialog, User, $translate, $uibModal) {
  
  $scope.success = false;

  
  $scope.user = {
    username: '',
    email: '',
    password: ''
  };
  

  $scope.register = function() {
    console.log('Registering user:', $scope.user);
    User.register($scope.user).then(function(response) {
      $scope.success = true;
      console.log('success, return:', {
        status: response.status,
        message: response.message
      });
      console.log('User registered successfully:', $scope.user);
    });
  };
});