package com.staxrt.tutorial.model;

// import java.io.Serializable;
// import java.sql.Date;

import javax.persistence.*;



@Entity
@Table(name = "order_customer_history")
public class OrderCustomer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_ID")
    private int orderId;

    // @ManyToOne
    @Column(name = "Customer_ID")
    private int customer;

   

    public OrderCustomer() {
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomer() {
        return customer;
    }

    public OrderCustomer(int orderId, int customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderCustomer [orderId=" + orderId + ", customer=" + customer + "]";
    }

   

    

}