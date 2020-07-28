package com.example.ordermanagementsystem.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table (name = "Orders")
public class Order {
    @Column
    private @Id @GeneratedValue Long orderId;
    @Column
    private String orderName;
    @Column
    private String customerName;
    @Column
    private String customerSurname;
    @Column
    private final LocalDate orderDate = LocalDate.now();

    public Order(){}

    public Order(String orderName, String customerName, String customerSurname) {
        this.orderName = orderName;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
               Objects.equals(orderName, order.orderName) &&
               Objects.equals(customerName, order.customerName) &&
               Objects.equals(customerSurname, order.customerSurname) &&
               Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderName, customerName, customerSurname, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
               "orderId=" + orderId +
               ", orderName='" + orderName + '\'' +
               ", customerName='" + customerName + '\'' +
               ", customerSurname='" + customerSurname + '\'' +
               ", orderDate=" + orderDate +
               '}';
    }
}
