package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.OrderProduct;
// import com.staxrt.tutorial.model.paymentdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct, Integer> {

    List<OrderProduct> findAllByOrderID(int order_id);

    // List<OrderProduct> findAllByOrderID();

    // List<Order> findByCustomerId(long customer_id);

    

     
    
}