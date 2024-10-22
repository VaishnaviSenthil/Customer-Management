package com.staxrt.tutorial.repository;



// import com.staxrt.tutorial.model.Customer;
import com.staxrt.tutorial.model.OrderCustomer;
// import com.staxrt.tutorial.model.paymentdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCustoRepository extends JpaRepository<OrderCustomer, Integer> {

    // OrderCustomer findByCustomerId(int customer_id);

    List<OrderCustomer> findAllByCustomer(int customerId);
    
}

