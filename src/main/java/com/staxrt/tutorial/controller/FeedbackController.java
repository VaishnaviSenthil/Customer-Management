


// package com.staxrt.tutorial.controller;

// import com.staxrt.tutorial.model.Feedback;
// import com.staxrt.tutorial.repository.FeedbackRepository;
// // import com.staxrt.tutorial.repository.UserRepository;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.CrossOrigin;


// @RestController
// @RequestMapping("/api/v2")

// public class FeedbackController{
//     @Autowired
//   private FeedbackRepository FeedbackRepository;


//   @CrossOrigin(origins = "http://springboot.thesmartsteps.com:8184")
//   @PostMapping("/feedback")
//   public Feedback createFeedback(@Valid @RequestBody Feedback feedback) {
//     return FeedbackRepository.save(feedback);
//   }
// }