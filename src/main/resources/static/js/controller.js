// User Controller

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
            alert("Your details have been updated successfully !")
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
                    console.log(response.data);
                    alert("Your details have been deleted successfully !")}
                });
    }
});

// Roles Controller

app.controller('rolesController', function($scope, $http,$window) {
    $scope.checkEmail = function() {
        console.log("calling the functionn...")

        $http({
            method : "POST",
            url : 'http://localhost:9003/api/v1/login',
            data : {
                "email":$scope.user.email,
                "password":$scope.user.password,
            }
        }).success(function(data) {
            console.log(data);
            alert("Login SuccessFull");
            $scope.profileDetails=data;
        })
    };

    
    $scope.forgotPassword = function() {
        var email=$scope.user.email;
        $http({
            method : "POST",
            url : 'http://localhost:9003/api/v1/forgotPassword',
            data : email
        })
        .then(function(response) {
            console.log(response.data);
            $scope.password=response.data.password;
            alert("Your password is :"+$scope.password);
            
        })
        .catch(function(error) {
            // $scope.alertMessage = "Error: " + error.data.message;
            alert("Email not Found"); 
        });
};
});

// Profile Controller

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

// Register Controller

app.controller('registerController', function($scope, $http, $window, $location) {
    $scope.headingTitle = "Register";
    $scope.disableGenerateOTP = false;
    $scope.isOTPValid = false;
   

    $scope.verifyEmail = function() {
        $http({
            method : "GET",
            url : 'http://localhost:9003/api/v1/checkEmail/' + $scope.user.email})
          .then(function(response) {
            if (response.data.exists) {
              alert('Email already exists');
              isformValid= false;
            }else{
                isformValid= true;
            }
            
           })
          
      };
    //   isEmailValid=$scope.verifyEmail();

    $scope.generateOTP = function() {
        isEmailValid=$scope.verifyEmail();
        // Generate a random number between 1000 and 9999
        $scope.otp = Math.floor(Math.random() * 9000) + 1000;
        $scope.otp2 = $scope.otp;
        alert("OTP generated: " + $scope.otp);
        // Disable the "Generate OTP" button after generating the OTP
        $scope.disableGenerateOTP = true;
    };

    $scope.validateOTP = function() {
        // Get the user-entered OTP
        var userEnteredOTP = parseInt($scope.otp1);
        console.log("Entered OTP :" +userEnteredOTP);
        console.log("Generated OTP :"+$scope.otp);

        // Check if the user-entered OTP matches the generated OTP
        $scope.isOTPValid = userEnteredOTP === $scope.otp;
    };
    

    $scope.submitForm = function() {
        // Validate the OTP first
        $scope.validateOTP();
   
        console.log(isEmailValid);
        // isFormValid = $scope.validateForm();

        // Check if OTP is valid
        if ($scope.isOTPValid )

        {
            
            // Proceed with form submission

            console.log("In if statement");
            $http({
                method: "POST",
                url: 'http://localhost:9003/api/v1/users',
                data: {
                    "firstName": $scope.user.firstName,
                    "lastName": $scope.user.lastName,
                    "email": $scope.user.email,
                    "password": $scope.user.password,
                    "phone": $scope.user.phone,
                    "address": $scope.user.address,
                    "profilepicture":$scope.user.avatarPath,
                    "createdBy": "Akilan",
                    "updatedBy": "Akilan"
                }
            }).success(function(data) {
                //after successfull register we redirect to login page in new window
                // $window.location.href="/views/roles.html";
                console.log(data);
                // Redirect or show success message to the user
                
            });
        } else {
            // Show alert that OTP is incorrect
            alert("form entered value  is incorrect. Please try again.");
        }
    };

});




   



