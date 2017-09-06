'use strict';
angular.module('myApp', [
    'ngRoute',
    'myApp.main',
    'ui.grid',
    'ui.grid.selection'
]).config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');
    $routeProvider.otherwise({redirectTo: '/main'});
}]);
