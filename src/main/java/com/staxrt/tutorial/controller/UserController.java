package com.staxrt.tutorial.controller;
import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Feedback;
import com.staxrt.tutorial.model.OrderCustomer;
import com.staxrt.tutorial.model.OrderProduct;
import com.staxrt.tutorial.model.User;
import com.staxrt.tutorial.model.paymentDetails;
import com.staxrt.tutorial.repository.FeedbackRepository;
import com.staxrt.tutorial.repository.OrderCustoRepository;
import com.staxrt.tutorial.repository.OrderRepository;
import com.staxrt.tutorial.repository.PaymentRepository;
import com.staxrt.tutorial.repository.UserRepository;
import com.staxrt.tutorial.service.EmailPassword;
import com.staxrt.tutorial.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

   @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }

  @Autowired
  private UserRepository userRepository;
  private FeedbackRepository feedbackRepository;
  public UserController(FeedbackRepository feedbackRepository) {
      this.feedbackRepository = feedbackRepository;
  }

  /**
   * Get all users list.
   *
   * @return the list
   */

  @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
  @GetMapping("/profiles")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  
  @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
  @GetMapping("/profiles/{id}")
  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") int userId)
      throws ResourceNotFoundException {
    User user =userRepository.findById(userId);
            // .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
    return ResponseEntity.ok().body(user);
  }


  @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
 @GetMapping("/profiles/name/{firstName}")
 public ResponseEntity<List<User>> getUsersByFirstName(@PathVariable(value = "firstName") String firstName) {
    List<User> users = userRepository.findByFirstName(firstName);
    if (users.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(users);
}

 

  @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
    User savedUser = userRepository.save(user);

    // Send welcome email to the user
    EmailService.sendWelcomeEmail(savedUser.getEmail());

    return savedUser;
  }


  
//   admin update
  @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
  @PutMapping("/update/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable(value = "id") int userId, @Valid @RequestBody User userDetails)
      throws ResourceNotFoundException {

    User user =
        userRepository
            .findById(userId);
            // .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    user.setEmail(userDetails.getEmail());
    final User updatedUser = userRepository.save(user);
    return ResponseEntity.ok(updatedUser);
  }
//   admin delete
  @CrossOrigin(origins="http://springboot.thesmartsteps.com:8184")
  @DeleteMapping("/delete/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws Exception {
    User user =
        userRepository
            .findById(userId);
            // .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    userRepository.delete(user);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
// user login 
  @CrossOrigin(origins="http://springboot.thesmartsteps.com:8184")
  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody User credentials, HttpSession session) {
    User user = userRepository.findByEmail(credentials.getEmail());
    if (user != null && user.getPassword().equals(credentials.getPassword())) {
        // Login successful, return the user object (or any other response you need)
        // HttpSession session = request.getSession();
        session.setAttribute("userDetails", user);
         session.setAttribute("name", user.getFirstName());
         session.setAttribute("id", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("phone", user.getPhone());
        return ResponseEntity.ok().body(user);
    } else {
        // Login failed, return null (or handle it as needed)
        return null;
    }
    
}
@CrossOrigin(origins="http://springboot.thesmartsteps.com:8184")
@GetMapping("/userDetails")
    public ResponseEntity<User> getUserDetails(HttpSession session) {
        User user = (User) session.getAttribute("userDetails");
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/updateUserDetails")
    public ResponseEntity<User> updateUserDetails(@RequestBody User updatedUser, HttpSession session) {
        User user = (User) session.getAttribute("userDetails");
        if (user != null) {
            // Update the user details with the data from the updatedUser
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setPassword(updatedUser.getPassword());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            // Update other user details as needed

            // Save the updated user object back to the session
            User savedUser = userRepository.save(user);

            // Save the updated user object back to the session
            session.setAttribute("userDetails", savedUser);
            return ResponseEntity.ok().body(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }




// Forgot password
@CrossOrigin(origins="http://springboot.thesmartsteps.com:8184")
@PostMapping("/forgotPassword")
public ResponseEntity<String> forgotPassword(@RequestBody String email) {
  User user = userRepository.findByEmail(email);
  System.out.println(user);
  if (user != null) {
    
    EmailPassword.sendForgotPasswordEmail(user.getEmail(), user.getPassword());
    System.out.println("Mail Sent Successfully");
     String password = user.getPassword();
      return ResponseEntity.ok("{\"password\": \"" + password + "\"}");
  } else {
      return ResponseEntity.notFound().build();   
  }
}

@CrossOrigin(origins="http://springboot.thesmartsteps.com:8184")
@GetMapping("/checkEmail/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        boolean exists = userRepository.existsByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins="http://springboot.thesmartsteps.com:8184")
    @RequestMapping("/afterlogin")
    public String afterLogin() {
        return "views/afterlogin.html";
    }

     @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
    @PostMapping("/feedback")
  public Feedback createFeedback(@Valid @RequestBody Feedback feedback, HttpSession session) {
    String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");
        Long phone = (Long) session.getAttribute("phone");
        feedback.setName(name);
        feedback.setEmail(email);
        feedback.setPhone(phone);
    return feedbackRepository.save(feedback);
  }
 
  @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
  @GetMapping("/getUsername")
  public ResponseEntity<String> getUsername(HttpSession session) {
    String name = (String) session.getAttribute("name");
    if (name != null) {
        // Create a JSON response with the user's name
        String jsonResponse = "{\"name\": \"" + name + "\"}";
        return ResponseEntity.ok(jsonResponse);
    } else {
        return ResponseEntity.ok("{\"message\": \"User name not found in session\"}");
    }
}


 @Autowired
    private PaymentRepository paymentRepository; // Make sure you have the PaymentRepository bean configured

    @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
    @GetMapping("/status")
    public ResponseEntity<List<paymentDetails>> getPaymentDetailsByCustomerId(HttpSession session)
            throws ResourceNotFoundException {
        // Retrieve customer ID from the session or wherever it's stored in your application
        int customerId = (int) session.getAttribute("id");
        // System.out.println(customerId);

        List<paymentDetails> paymentdetails = paymentRepository.findByCustomerId(customerId);

        if (paymentdetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(paymentdetails);
    }
 
     @Autowired
    private OrderCustoRepository orderCusRepository;
    @Autowired
    private OrderRepository orderRepository;
 
    // private OrderRepository orderRepository; 

    @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
@GetMapping("/products")
public ResponseEntity<List<OrderProduct>> getOrderDetailsByCustomerId(HttpSession session)
            throws ResourceNotFoundException {
        // Retrieve customer ID from the session or wherever it's stored in your application
        int customerId = (int) session.getAttribute("id");
        System.out.println("=====================================================================");
        System.out.println(customerId);
        System.out.println("=====================================================================");

        List<OrderCustomer> orderDetails = orderCusRepository.findAllByCustomer(customerId);
        System.out.println("=====================================================================");
        System.out.println(orderDetails);
        System.out.println("=====================================================================");
        // ArrayList<Integer> productDetails = new ArrayList<>();
        ArrayList<Integer> orderDetail = new ArrayList<>();
        ArrayList<OrderProduct> ordPro = new ArrayList<>();
       
         for(OrderCustomer ord : orderDetails){
            System.out.println(ord);
            int id = ord.getOrderId();
            System.out.println(id);
            orderDetail.add(id);
        }
        System.out.println(orderDetail);
        for (Integer Ord : orderDetail){
            System.out.println("000000000000000");
            System.out.println(Ord);
            List<OrderProduct> order = orderRepository.findAllByOrderID(Ord); 
            System.out.println("1111111111111");
            System.out.println(order);
            for(OrderProduct or : order){
                ordPro.add(or);
            }
        }
        return ResponseEntity.ok(ordPro);


       
        
        
    }
}


    





