var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/users',{
            templateUrl: '/views/users.html',
            controller: 'usersController'
        })
        .when('/roles',{
            templateUrl: '/views/roles.html',
            controller: 'rolesController'
        })
        .when('/profile',{
            templateUrl: '/views/profile.html',
            controller: 'profileController'
        })
        .when('/register',{
            templateUrl: '/views/register.html',
            controller: 'registerController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

