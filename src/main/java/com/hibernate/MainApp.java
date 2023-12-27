package com.hibernate;

import java.util.Scanner;

import com.hibernate.dao.CustomerDao;
import com.hibernate.dao.ProductDao;
import com.hibernate.models.Customer;
import com.hibernate.models.Product;

/**
 * Module       : CRM (Customer Relationship Management)
 * Created By   : Umesh Kumar 
 * Date         : 23-12-2023
 * Phone        : 6201033951
 *
 */
public class MainApp 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        int choice; 
        do{
            System.out.println( "\n<=============================== CRM System ===============================>\n" );
            System.out.println("\n1. Add Customer \t\t2. Add Product \t\t3.Order Product \n\n0.Exit.");
            System.out.print("\nEnter Your Choice : ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\n<-------------------Adding Customer ------------------->\n");
                    System.out.print("Enter Customer Name : ");
                    String name = sc.nextLine();
                    System.out.print("\nEnter Email-Id : ");
                    String email = sc.nextLine();
                    System.out.print("\nEnter Phone No : ");
                    Long phone = sc.nextLong();
                    sc.nextLine();
                    System.out.print("\nEnter Address : ");
                    String address = sc.nextLine();

                    Customer c = new Customer();
                    c.setName(name);
                    c.setEmail(email);
                    c.setAddress(address);
                    c.setPhone(phone);

                    CustomerDao.addCustomer(c); // Adding customer
                    break;
                case 2:
                    System.out.println("\n<-------------------Adding Product ------------------->\n");
                    System.out.print("\nEnter Product Id : ");
                    int p_id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("\nEnter Product Name : ");
                    String p_name = sc.nextLine();
                    System.out.print("\nEnter Product Price : ");
                    float p_price = sc.nextFloat();
                    sc.nextLine();

                    Product product = new Product();
                    product.setId(p_id);
                    product.setPrice(p_price);
                    product.setProductName(p_name);

                    ProductDao.addProduct(product); // Adding product
                    break;
                default:
                    System.out.println("\nInvalid Choice, Please try again !!!");
                    break;
            }
        }while(choice != 0);
        
    }
}

