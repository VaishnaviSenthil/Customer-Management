package com.staxrt.tutorial.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "order_product_history")
@IdClass(CustomerCartProductId.class)
public class OrderProduct {    
    @Id
    @Column(name = "Order_ID")
    private int orderID;

    @Id
    @Column(name = "Product_ID")
    private int productID;

    @Column(name = "Product_Name")
    private String product_Name;

    @Column(name = "Unit_Of_Measure")
    private String unit_Of_Measure;

    @Column(name = "Product_Quantity")
    private int product_Quantity;

    @Column(name = "Product_Price")
    private double product_Price;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getUnit_Of_Measure() {
        return unit_Of_Measure;
    }

    public void setUnit_Of_Measure(String unit_Of_Measure) {
        this.unit_Of_Measure = unit_Of_Measure;
    }

    public int getProduct_Quantity() {
        return product_Quantity;
    }

    public void setProduct_Quantity(int product_Quantity) {
        this.product_Quantity = product_Quantity;
    }

    public double getProduct_Price() {
        return product_Price;
    }

    public void setProduct_Price(double product_Price) {
        this.product_Price = product_Price;
    }

    @Override
    public String toString() {
        return "OrderProduct [orderID=" + orderID + ", productID=" + productID + ", product_Name=" + product_Name
                + ", unit_Of_Measure=" + unit_Of_Measure + ", product_Quantity=" + product_Quantity + ", product_Price="
                + product_Price + "]";
    }

    
}