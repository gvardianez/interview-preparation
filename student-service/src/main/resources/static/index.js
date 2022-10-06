angular.module('student-service', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/student-service/api/v1';

    $scope.loadStudents = function (pageIndex = 1) {
        $http({
            url: contextPath + '/students',
            method: 'GET',
            params: {
                p: pageIndex,
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_age: $scope.filter ? $scope.filter.min_age : null,
                max_age: $scope.filter ? $scope.filter.max_age : null
            }
        }).then(function (response) {
            $scope.studentsPage = response.data;
        });
    };

    $scope.deleteStudent = function (studentId) {
        $http.delete(contextPath + '/students/' + studentId)
            .then(function (response) {
                $scope.loadStudents();
            });
    }

    $scope.createStudent = function () {
        console.log($scope.newStudent);
        $http.post(contextPath + '/students', $scope.newStudent)
            .then(function (response) {
                $scope.loadStudents();
            });
    }

    $scope.loadStudents();
});