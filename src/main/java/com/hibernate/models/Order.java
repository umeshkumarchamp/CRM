package com.hibernate.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    private Date orderDate; 
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "order_products", 
        joinColumns = @JoinColumn(name="order_id"), 
        inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<Product> products = new HashSet<Product>();


    public Order() {
    }

    public Order(int id, Date orderDate, Date deliveryDate, Customer customer, Set<Product> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.customer = customer;
        this.products = products;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", products='" + getProducts() + "'" +
            "}";
    }

}
