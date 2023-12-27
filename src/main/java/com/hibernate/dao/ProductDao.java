package com.hibernate.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.config.HibernateUtil;
import com.hibernate.models.Product;

public class ProductDao {
    
    /**
     * Add a new Product to the database
     * @param product
     */
    public static void addProduct(Product product){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            Transaction tran = session.beginTransaction();

            Serializable productId = (Serializable) session.save(product);
            if(productId != null){
                System.out.println("\nProduct Added successfully with Id : " + productId);
            }else{
                System.out.println("\nSomething went wrong !");
            }
            tran.commit();
        }
    }
}
