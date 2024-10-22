
package com.staxrt.tutorial.repository;


import com.staxrt.tutorial.model.paymentDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<paymentDetails,Integer> {

     List<paymentDetails> findByCustomerId(int customer_id);
    // You can add custom query methods here if needed
}