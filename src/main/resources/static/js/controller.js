app.controller('usersController', function($scope,$http) {
    $scope.headingTitle = "Update";

    $scope.submitForm = function() {
        console.log("calling the functionn...")

        $http({
            method : "PUT",
            url : 'http://localhost:9003/api/v1/update/'+$scope.user.id,
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
    $scope.deleteForm=function(){

        $http({
            method:"DELETE",
            url:"http://localhost:9003/api/v1/delete/"+$scope.id,
            }).then(function successCallback(response) {
                console.log(response);
                if (response.status == 200) {
                    console.log("success");
                    console.log(response.data);}
                });
    }
});

app.controller('rolesController', function($scope) {
    $scope.headingTitle = "Roles List";
});




app.controller('profileController', function($scope, $http) {
    $scope.headingTitle = "Profile page";
    // $scope.countryDetails=[];
    $scope.id =''; // Initialize the firstName variable
    
    // Update the API URL to include the first name as a query parameter
    $scope.getCountry = function() {
        console.log("inside the function");
        // console.log($scope.firstName);
        $http({
            method: 'GET',
            url: "http://localhost:9003/api/v1/profiles/",
        }).then(function successCallback(response) {
            console.log(response);
            if (response.status == 200) {
                console.log("success");
                console.log(response.data);
                // $scope.countryDetails.push(response.data);
                $scope.countryDetails=response.data;
                // console.log(countryDetails);
                // console.log($scope.countryDetails);
            } else {
                console.log("errorrrrrr");
            }
        }, function errorCallback(response) {
            console.log(response);
            $scope.countryDetails = "";
        });
    };

    $scope.countryDetails=[];
    $scope.getCountrybyId = function() {
        console.log("inside the function");
        // console.log($scope.firstName);
        $http({
            method: 'GET',
            url: "http://localhost:9003/api/v1/profiles/"+$scope.id,
        }).then(function successCallback(response) {
            console.log(response);
            if (response.status == 200) {
                console.log("success");
                console.log(response.data);
                $scope.countryDetails.push(response.data);
                // $scope.countryDetails=response.data;
                // console.log(countryDetails);
                // console.log($scope.countryDetails);
            } else {
                console.log("errorrrrrr");
            }
        }, function errorCallback(response) {
            console.log(response);
            $scope.countryDetails = "";
        });
    };
});





app.controller('registerController', function($scope, $http) {
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



