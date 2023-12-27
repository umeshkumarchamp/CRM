package com.hibernate;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.config.HibernateUtil;
import com.hibernate.dao.CustomerDao;
import com.hibernate.dao.OrderDao;
import com.hibernate.dao.ProductDao;
import com.hibernate.models.Customer;
import com.hibernate.models.Order;
import com.hibernate.models.Product;

/**
 * Module : CRM (Customer Relationship Management)
 * Created By : Umesh Kumar
 * Date : 23-12-2023
 * Phone : 6201033951
 *
 */
public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n\n<=============================== CRM System ===============================>\n");
            System.out.println(
                    "\n1. Add Customer \t\t2. Add Product \t\t\t3.Order Product\n\n4. Get Order Detail \t\t5. Get Customer Detail \t\t6. Get Product Detail \n\n0.Exit.");
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

                    Product p = new Product();
                    p.setId(p_id);
                    p.setPrice(p_price);
                    p.setProductName(p_name);

                    ProductDao.addProduct(p); // Adding product
                    break;

                case 3:
                    System.out.println("\n<------------------- Place Order ------------------->\n");
                    System.out.print("\nEnter Customer Id : ");
                    int customerId = sc.nextInt();
                    System.out.print("\nEnter Product Id : ");
                    int productId = sc.nextInt();
                    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                    try (Session session = sessionFactory.openSession()) {
                        Customer customer = session.get(Customer.class, customerId);
                        Product product = session.get(Product.class, productId);
                        if (customer == null) {
                            System.out.println("Customer not found !");
                        } else if (product == null) {
                            System.out.println("Product not found !");
                        } else {
                            Set<Product> products = new HashSet<>();
                            products.add(product);

                            Order order = new Order();
                            order.setCustomer(customer);
                            order.setProducts(products);
                            long timestamp = System.currentTimeMillis();
                            order.setDeliveryDate(new Date(timestamp));
                            order.setOrderDate(new Date(timestamp));

                            OrderDao.placeOrder(order);
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n<------------------- Get Order Details By Id ------------------->\n");
                    System.out.print("\nEnter Your Order Id : ");
                    int orderId = sc.nextInt();
                    Order fetchOrder = OrderDao.getOrderById(orderId);
                    if (fetchOrder == null) {
                        System.out.println("Order not found !");
                    } else {
                        System.out.print("\n<------------------- Order Details ------------------->\n");
                        System.out.print("\nOrder Id : " + fetchOrder.getId());
                        System.out.print("\nCustomer Name : " + fetchOrder.getCustomer().getName());
                        System.out.print("\nProduct Details : " + fetchOrder.getProducts());
                        System.out.print("\nOrder Date : " + fetchOrder.getOrderDate());
                        System.out.print("\nDelivery Date : " + fetchOrder.getDeliveryDate());

                    }
                    break;

                case 5:
                    System.out.println("\n<------------------- Get Customer Details By Id ------------------->\n");
                    System.out.print("\nEnter Your Customer Id : ");
                    customerId = sc.nextInt();
                    Customer fetchCustomer = CustomerDao.getCustomerById(customerId);
                    if (fetchCustomer == null) {
                        System.out.println("Order not found !");
                    } else {
                        System.out.print("\n<------------------- Customer Details ------------------->\n");
                        System.out.print("\nCustomer Id : " + fetchCustomer.getId());
                        System.out.print("\nCustomer Name : " + fetchCustomer.getName());
                        System.out.print("\nEmail-Id : " + fetchCustomer.getEmail());
                        System.out.print("\nPhone : " + fetchCustomer.getPhone());
                        System.out.print("\nAddress : " + fetchCustomer.getAddress());

                    }
                    break;

                case 6:
                    System.out.println("\n<------------------- Get Product Details By Id ------------------->\n");
                    System.out.print("\nEnter Your Product Id : ");
                    productId = sc.nextInt();
                    Product fetchProduct = ProductDao.getProductById(productId);
                    if (fetchProduct == null) {
                        System.out.println("Product not found !");
                    } else {
                        System.out.print("\n<------------------- Product Details ------------------->\n");
                        System.out.print("\nProduct Id : " + fetchProduct.getId());
                        System.out.print("\nProduct Name : " + fetchProduct.getProductName());
                        System.out.print("\nPrice : " + fetchProduct.getPrice());
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid Choice, Please try again !!!");
                    break;
            }
        } while (choice != 0);

        sc.close();
    }
}
