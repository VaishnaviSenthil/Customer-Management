package com.staxrt.tutorial.model;
 import org.springframework.data.jpa.domain.support.AuditingEntityListener;



// import java.util.UUID;

import javax.persistence.*;
//  

@Entity
@Table(name = " payment_details")
@EntityListeners(AuditingEntityListener.class)

public class paymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

   

   @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column (name="payment_status", nullable=false)
    private String status="pending";

    
     @Column(name = "order_id", nullable = false)
    private int orderId;

     @Column(name = "invoice_Total", nullable = false)
    private int price;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "paymentdetails [customerId=" + customerId + ", status=" + status + ", orderId=" + orderId + ", price="
                + price + "]";
    }


}
   

    

