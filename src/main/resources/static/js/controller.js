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

// Login Controller

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
            // alert("Login SuccessFull");
            if(data && data.id){
                
                $window.location.href = 'views/afterlogin.html';
            }
            else{
                alert("Your user_id or password does'nt match ");
            }
        })
        
    };
    $scope.redirectToRegister = function() {
        $window.location.href = 'views/register.html';
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

 //product controller

 app.controller('productController',function($scope,$http){
    $scope.user=function(){
        
};

})

 //feedback controller

 app.controller('feedbackController',function($scope,$http,$window){
   $scope.submitFeedback=function(){
    $http({
        method: "POST",
        url: 'http://localhost:9003/api/v2/feedback',
        data: {
            "name" :$scope.user.name,
            "email" : $scope.user.email,
            "phone": $scope.user.phone,
            "question1" : $scope.user.question1,
            "question2": $scope.user.question2,
            "question3" : $scope.user.question3,
            "message" : $scope.user.message
        }
    }).then(function(data) {
       
        console.log(data);
        alert("Feedback Sent Successfully");
        $window.location.reload();
        
    }).catch(function(error) {
        console.log(error);
    });
        
};

})
// your profile controller

app.controller('yourProfileController',function($scope,$http){
   $scope.profileDetails=[];
    $scope.getDetail = function() {
        console.log("inside the function");
        $http({
            method: 'GET',
            url: "http://localhost:9003/api/v1/userDetails",
        }).then(function(response) {
            // On successful response, update the profileDetails in the scope
            $scope.profileDetails.push(response.data);
            console.log($scope.profileDetails);
        })
        .catch(function(error) {
            // Handle error if necessary
            console.log(error);
        });
};

$scope.editMode = false;

$scope.toggleEditMode = function() {
    $scope.editMode = !$scope.editMode;
};
$scope.updateForm = function() {
    console.log("calling the functionn...")

    $http({
        method : "POST",
        url : 'http://localhost:9003/api/v1/updateUserDetails/',
        data : {
            "firstName":$scope.user.firstName,
            "lastName":$scope.user.lastName,
            "password":$scope.user.password,
            "phone":$scope.user.phone,
            "address":$scope.user.address,
        }
    }).success(function(data) {
        console.log(data);
        $scope.editMode = false;
        alert("Your details have been updated successfully !")
    })
};

});

// Profile Controller

app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; // Convert to integer
        return input.slice(start);
    };
});

app.controller('profileController', function($scope, $http) {
    $scope.headingTitle = "Profile page";
    $scope.id =''; 
    $scope.currentPage = 0;
    $scope.pageSize = 10;

    $scope.getProfile = function() {
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
                $scope.profileDetails=response.data;
                
            } else {
                console.log("errorrrrrr");
            }

            $scope.totalPages = Math.ceil($scope.profileDetails.length / $scope.pageSize);

                // Function to navigate to the previous page
                $scope.prevPage = function() {
                    if ($scope.currentPage > 0) {
                        $scope.currentPage--;
                    }
                };
            
                // Function to navigate to the next page
                $scope.nextPage = function() {
                    if ($scope.currentPage < $scope.totalPages - 1) {
                        $scope.currentPage++;
                    }
                };
            



        }, function errorCallback(response) {
            console.log(response);
            $scope.profileDetails = "";
        });
    };

    $scope.profileDetails=[];
    $scope.getProfilebyId = function() {
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
                $scope.profileDetails.push(response.data);
                
            } else {
                console.log("errorrrrrr");
            }
        }, function errorCallback(response) {
            console.log(response);
            $scope.profileDetails = "";
        });
    };


    $scope.profileDetails=[];
    $scope.getProfilebyFirstName = function() {
        console.log("inside the function");
        // console.log($scope.firstName);
        $http({
            method: 'GET',
            url: "http://localhost:9003/api/v1/profiles/name/"+$scope.firstName,
        }).then(function successCallback(response) {
            console.log(response);
            if (response.status == 200) {
                console.log("success");
                console.log(response.data);
                $scope.profileDetails=response.data;
            } else {
                console.log("errorrrrrr");
            }
        }, function errorCallback(response) {
            console.log(response);
            $scope.profileDetails = "";
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
            }).then(function(data) {
               
                console.log(data);
                alert("Registration Successful");
                $window.location.href="/";
                // Redirect or show success message to the user
                
            }).catch(function(error) {
                console.log(error);
            });
        } else {
            // Show alert that OTP is incorrect
            alert("form entered value  is incorrect. Please try again.");
        }
    };
    

});




   



