package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.User;
import com.staxrt.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import javax.validation.Valid;
import java.util.Date;
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

  /**
   * Get all users list.
   *
   * @return the list
   */

  @CrossOrigin(origins = "http://localhost:9003")
  @GetMapping("/profiles")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  
  @CrossOrigin(origins = "http://localhost:9003")
  @GetMapping("/profiles/{id}")
  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
      throws ResourceNotFoundException {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
    return ResponseEntity.ok().body(user);
  }


  @CrossOrigin(origins = "http://localhost:9003")
 @GetMapping("/profiles/name/{firstName}")
 public ResponseEntity<List<User>> getUsersByFirstName(@PathVariable(value = "firstName") String firstName) {
    List<User> users = userRepository.findByFirstName(firstName);
    if (users.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(users);
}

 
  
  @CrossOrigin(origins = "http://localhost:9003")
  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
    return userRepository.save(user);
  }

  @CrossOrigin(origins = "http://localhost:9003")
  @PutMapping("/update/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
      throws ResourceNotFoundException {

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    user.setEmail(userDetails.getEmail());
    user.setLastName(userDetails.getLastName());
    user.setFirstName(userDetails.getFirstName());
    user.setPassword(userDetails.getPassword());
    user.setPhone(userDetails.getPhone());
    user.setAddress(userDetails.getAddress());
    user.setUpdatedAt(new Date());
    final User updatedUser = userRepository.save(user);
    return ResponseEntity.ok(updatedUser);
  }

  @CrossOrigin(origins="http://localhost:9003")
  @DeleteMapping("/delete/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    userRepository.delete(user);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }

  @CrossOrigin(origins="http://localhost:9003")
  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody User credentials, HttpSession session) {
    User user = userRepository.findByEmail(credentials.getEmail());
    if (user != null && user.getPassword().equals(credentials.getPassword())) {
        // Login successful, return the user object (or any other response you need)
        // HttpSession session = request.getSession();
        session.setAttribute("userDetails", user);
        return ResponseEntity.ok().body(user);
    } else {
        // Login failed, return null (or handle it as needed)
        return null;
    }
    
}
@CrossOrigin(origins="http://localhost:9003")
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
@CrossOrigin(origins="http://localhost:9003")
@PostMapping("/forgotPassword")
public ResponseEntity<String> forgotPassword(@RequestBody String email) {
  User user = userRepository.findByEmail(email);
  if (user != null) {
      String password = user.getPassword();
      return ResponseEntity.ok("{\"password\": \"" + password + "\"}");
  } else {
      return ResponseEntity.notFound().build();   
  }
}

@CrossOrigin(origins="http://localhost:9003")
@GetMapping("/checkEmail/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        boolean exists = userRepository.existsByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins="http://localhost:9003")
    @RequestMapping("/afterlogin")
    public String afterLogin() {
        return "views/afterlogin.html";
    }
}


    





