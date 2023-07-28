app.controller('usersController', function($scope) {
    $scope.headingTitle = "User List";
});

app.controller('rolesController', function($scope) {
    $scope.headingTitle = "Roles List";
});

app.controller('profileController', function($scope,$http) {
    $scope.headingTitle = "Profile page";
    $scope.countryName = "";
            $scope.countryUrl = "http://localhost:9003/api/v1/users";
            $scope.untrgetCoy = () => {
                console.log("inside the function");
                console.log($scope.countryUrl);
                    $http({
                      method: 'GET',
                      url: $scope.countryUrl,
                    }).then(function successCallback(response) {
                        console.log(response);
                        if(response.status == 200){
                            console.log("successs")
                            console.log(response.data)
                            $scope.countryDetails = response.data;
                        }
                        else{
                            console.log("errorrrrrr")
                        }
                      }, function errorCallback(response) {
                        console.log(response);
                        $scope.countryDetails = "";
                      });
            }
});

app.controller('registerController', function($scope,$http) {
    $scope.headingTitle = "Register";

    $scope.submitForm = function() {
        console.log("calling the functionn...")

        $http({
            method : "POST",
            url : 'http://localhost:9003/api/v1/users',
            data : {
                "firstName":$scope.user.firstName,
                "lastName":$scope.user.lastName,
                "email":$scope.user.email,
                "password":$scope.user.password,
                "phone":$scope.user.phone,
                "address":$scope.user.address,
                "createdBy":"Akilan",
                "updatedBy":"Akilan"
            }
        }).success(function(data) {
            console.log(data);
            //state.go(to verification page for job seeker
        })
    };
});



