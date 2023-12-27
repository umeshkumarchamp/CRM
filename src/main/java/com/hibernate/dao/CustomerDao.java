package com.hibernate.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.config.HibernateUtil;
import com.hibernate.models.Customer;
import com.hibernate.models.Order;

public class CustomerDao {
    
    /**
     * Adding a new Customer
     * @param customer
     */
    public static void addCustomer(Customer customer) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        try(Session session = sessionFactory.openSession()){
            Transaction tran = session.beginTransaction(); 
            Serializable customerId = (Serializable) session.save(customer);
            System.out.println("Customer Added with Id : " + customerId);
            tran.commit();
        }

    }

    /**
     * Get Customer List
     */
    public static void getCustomerList(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){

        }
    }

    public static Customer getCustomerById(int customerId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        try(Session session = sessionFactory.openSession()){
            return session.get(Customer.class, customerId);
        }
    }
}
