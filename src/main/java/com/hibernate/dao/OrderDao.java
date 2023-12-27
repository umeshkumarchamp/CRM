package com.hibernate.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.config.HibernateUtil;
import com.hibernate.models.Order;

public class OrderDao {

    /**
     * Create an order with the customer and product
     * @param order
     */
    public static void placeOrder(Order order){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();

            Serializable orderId = (Serializable) session.save(order);
            System.out.println("\nOrder Successfully Placed with Order Id : " +orderId);

            transaction.commit();
        }
    }

    public static Order getOrderById(int orderId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            return session.get(Order.class, orderId);
        }
    }

}
