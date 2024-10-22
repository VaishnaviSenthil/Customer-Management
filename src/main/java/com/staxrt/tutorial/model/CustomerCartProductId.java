package com.staxrt.tutorial.model;



import java.io.Serializable;
import java.util.Objects;

public class CustomerCartProductId implements Serializable {

    private int orderID;
    private int productID;

    // Constructors, equals, and hashCode methods...

    // Constructors
    public CustomerCartProductId() {
    }

    public CustomerCartProductId(int orderID, int productID) {
        this.orderID = orderID;
        this.productID = productID;
    }

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

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCartProductId that = (CustomerCartProductId) o;
        return orderID == that.orderID &&
                productID == that.productID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, productID);
    }
}