var app = angular.module('app', ['ngRoute', 'ngResource']);

app.config(function($routeProvider) {
  $routeProvider
    .when('/users', {
      templateUrl: '/views/users.html',
      controller: 'usersController'
    })
    .when('/roles', {
      templateUrl: '/views/index.html',
      controller: 'indexController'
    })
    .when('/profile', {
      templateUrl: '/views/profile.html',
      controller: 'profileController'
    })
    .when('/register', {
      templateUrl: '/views/register.html',
      controller: 'registerController'
    })
    .when('/viewProducts', {
      templateUrl: '/views/productlist.html',
      controller: 'productController'
    })
    .when('/feedback', {
      templateUrl: '/views/feedbackform.html',
      controller: 'feedbackController'
    })
    .when('/yourProfile', {
      templateUrl: '/views/yourProfile.html',
      controller: 'yourProfileController'
    })
    .otherwise({
      redirectTo: '/'
    });
});
