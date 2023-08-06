
package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // You can add custom query methods here if needed
}



