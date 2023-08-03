/*
 *
 *  Copyright (c) 2018-2020 Givantha Kalansuriya, This source is a part of
 *   Staxrt - sample application source code.
 *   http://staxrt.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.User;
import com.staxrt.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
  public User login(@RequestBody User credentials) {
    User user = userRepository.findByEmail(credentials.getEmail());
    if (user != null && user.getPassword().equals(credentials.getPassword())) {
        // Login successful, return the user object (or any other response you need)
        return user;
    } else {
        // Login failed, return null (or handle it as needed)
        return null;
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
}




