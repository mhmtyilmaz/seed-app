'use strict';
angular.module('myApp.main', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/main', {
            templateUrl: 'seed/main.html',
            controller: 'MainCtrl'
        });
    }])
    .controller('MainCtrl', ['$scope', '$http', '$q', '$log', '$timeout', function ($scope, $http, $q, $log, $timeout) {
        var baseUrl = 'http://localhost:8080';
        $scope.buttonName = "Add Employee";
        var getAllEmployee = function () {
            var defered = $q.defer();
            $http({
                method: 'GET',
                url: baseUrl + '/api/employee'
            }).then(function (response) {
                defered.resolve(response.data);
            }, function (error) {
                $log.error(error.message);
                defered.reject(error);
            });
            return defered.promise;
        };

        var getEmployeeById = function (id) {
            var defered = $q.defer();
            $http({
                method: 'GET',
                url: baseUrl + '/api/employee/' + id
            }).then(function (response) {
                defered.resolve(response.data);
            }, function (error) {
                $log.error(error.message);
                defered.reject(error);
            });
            return defered.promise;
        };

        var addOrUpdateEmployee = function (data) {
            var defered = $q.defer();
            $http({
                method: data.id == undefined ? 'PUT' :'POST',
                url: baseUrl + '/api/employee',
                data: data
            }).then(function (response) {
                defered.resolve(response.data);
            }, function (error) {
                $log.error(error.message);
                defered.reject(error);
            });
            return defered.promise;
        };

        getAllEmployee().then(function (resp) {
             for (var i = 0; i < resp.data.length; i++) {
                    resp.data[i].birthDate = new Date(resp.data[i].birthDate);
             }
            $scope.gridOptions.data = resp.data;
            $scope.isNoData = resp.data.length;
        });

        $scope.gridOptions = {
            columnDefs: [
                {field: 'name'},
                {field: 'birthDate', name: 'Birth Date'},
                {field: 'education', name: 'Education'},
                {field: 'deptName', name: 'Department Name'},
                {field: 'title', name: 'Title'}
            ],
            enableRowSelection: false,
            enableRowHeaderSelection: true,
            multiSelect: false
        };

        $scope.gridOptions.onRegisterApi = function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                $scope.id = row.entity.id;
                $scope.name = row.entity.name;
                $scope.birthDate = row.entity.birthDate;
                $scope.education = row.entity.education;
                $scope.deptName = row.entity.deptName;
                $scope.title = row.entity.title;
                $scope.buttonName = $scope.id != undefined ? "Update Employee" : "Add Employee";

            });
        }


        $scope.saveEmployee = function () {
            var newData = {
                id : $scope.id,
                name: $scope.name,
                birthDate: $scope.birthDate,
                education: $scope.education,
                deptName : $scope.deptName,
                title : $scope.title
            };

            addOrUpdateEmployee(newData).then(function (response) {
                getAllEmployee().then(function (resp) {
                    for (var i = 0; i < resp.data.length; i++) {
                        resp.data[i].birthDate = new Date(resp.data[i].birthDate);
                    }
                    $scope.gridOptions.data = resp.data;
                    $scope.isNoData = response.data.length;
                    $scope.id = null;
                    $scope.name = null;
                    $scope.birthDate = null;
                    $scope.education = null;
                    $scope.deptName = null;
                    $scope.title = null;
                    $scope.buttonName = "Add Employee";
                });
            });
        }

    }]);