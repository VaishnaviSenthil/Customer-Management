// User Controller

// app.controller('usersController', function($scope,$http) {
//     $scope.headingTitle = "Update";

//     $scope.submitForm = function() {
//         console.log("calling the functionn...")

//         $http({
//             method : "PUT",
//             url : 'http://springboot.thesmartsteps.com:8184/api/v1/update/'+$scope.user.id,
//             data : {
//                 "firstName":$scope.user.firstName,
//                 "lastName":$scope.user.lastName,
//                 "email":$scope.user.email,
//                 "password":$scope.user.password,
//                 "phone":$scope.user.phone,
//                 "address":$scope.user.address,
//                 "createdBy":$scope.user.firstName,
//                 "updatedBy":"Akilan"
//             }
//         }).success(function(data) {
//             console.log(data);
//             alert("Your details have been updated successfully !")
//         }).error(function(error) {
//             console.error(error);
//             alert("This email already exixts . Try to update using new email.");
//         })
//     };
//     $scope.deleteForm=function(){

//         $http({
//             method:"DELETE",
//             url:"http://springboot.thesmartsteps.com:8184/api/v1/delete/"+$scope.id,
//             }).then(function successCallback(response) {
//                 console.log(response);
//                 if (response.status == 200) {
//                     console.log("success");
//                     console.log(response.data);
//                     alert("Your details have been deleted successfully !")}
//                 });
//     }
// });

// Login Controller

app.controller('rolesController', function($scope, $http,$window) {
    $scope.checkEmail = function() {
        console.log("calling the functionn...")

        $http({
            method : "POST",
            url : 'http://springboot.thesmartsteps.com:8184/api/v1/login',
            data : {
                "email":$scope.user.email,
                "password":$scope.user.password,
            }
        }).success(function(data) {
            console.log(data);
            // alert("Login SuccessFull");
            if (data && data.id) {
                if ($scope.user.email.toLowerCase() === 'admin@hibiz.com') {
                    $window.location.href = 'views/adminafterlogin.html';
                } else {
                    $window.location.href = 'views/afterlogin.html';
                }
            } else {
                alert("Your user_id or password doesn't match.");
            }
        });
        
    };
    $scope.redirectToRegister = function() {
        $window.location.href = 'views/register.html';
    };

    
    $scope.forgotPassword = function() {
        var email=$scope.user.email;
        $http({
            method : "POST",
            url : 'http://springboot.thesmartsteps.com:8184/api/v1/forgotPassword',
            data : email
        })
        .then(function(response) {
            console.log(response.data);
            // $scope.password=response.data.password;
            alert("Your password has been sent to your registered mail");
            
        })
        .catch(function(error) {
            // $scope.alertMessage = "Error: " + error.data.message;
            alert("Your mail has not been registered "); 
        });
};
});

//userProfile Controller

app.controller('userProfileController',function($scope){
    $scope.user=function(){
        
};
})
 //product controller

 app.controller('productController',function($scope){
    $scope.user=function(){
        
};

})

 //feedback controller

 app.controller('feedbackController',function($scope,$http,$window){
    $scope.submitFeedback=function(){
     $http({
         method: "POST",
         url: 'http://springboot.thesmartsteps.com:8184/api/v1/feedback',
         data: {
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
            url: "http://springboot.thesmartsteps.com:8184/api/v1/userDetails",
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
        url : 'http://springboot.thesmartsteps.com:8184/api/v1/updateUserDetails/',
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





// Register Controller

app.controller('registerController', function($scope, $http, $window) {
    $scope.headingTitle = "Register";
    $scope.disableGenerateOTP = false;
    $scope.isOTPValid = false;
   

    $scope.verifyEmail = function() {
        $http({
            method : "GET",
            url : 'http://springboot.thesmartsteps.com:8184/api/v1/checkEmail/' + $scope.user.email})
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
                url: 'http://springboot.thesmartsteps.com:8184/api/v1/users',
                data: {
                    "firstName": $scope.user.firstName,
                    "lastName": $scope.user.lastName,
                    "email": $scope.user.email,
                    "password": $scope.user.password,
                    "phone": $scope.user.phone,
                    "address": $scope.user.address,
                    "profilepicture":$scope.user.avatarPath,
                    "createdBy": "Customer",
                    "updatedBy": "Admin"
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



app.controller('OrderController', function($scope, $http) {
    $scope.headingTitle = "Order page";
    $scope.orderid =''; 
   
        console.log("inside the function");
        $http({
            method: 'GET',
            url: "http://springboot.thesmartsteps.com:8184/api/v1/products",
        }).then(function(response) {
            $scope.OrderDetails=response.data;
            console.log($scope.OrderDetails);
        })
        .catch(function(error) {
            console.error('Error fetching orders:', error);
        });
            
    });
   
// payment controller

app.controller('PaymentController', function($scope, $http) {
    $scope.headingTitle = "payment page";
   
        console.log("inside the function");
        $http({
            method: 'GET',
            url: "http://springboot.thesmartsteps.com:8184/api/v1/status",
        }).then(function(response) {
            $scope.paymentDetails=response.data;
            console.log($scope.paymentDetails);
        })
        .catch(function(error) {
            console.error('Error fetching orders:', error);
        });
            
    });