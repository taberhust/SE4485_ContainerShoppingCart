/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import DBConnection.DBConnection;
import Entity.Cart;
import Entity.Container;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matt
 */
public class CartDaoImplTest {

    /**
     * Test of createCart method, of class CartDaoImpl.
     */
    @Test
    public void testCreateCart_3args_1() throws Exception {
        System.out.println("createCart");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        ContainerDaoImpl containerInstance = new ContainerDaoImpl();
        Container container = containerInstance.retrieveContainer(connection, 1L);
        Long userID = 1L;
        
        CartDaoImpl instance = new CartDaoImpl();
        instance.createCart(connection, userID, container);
        
        ArrayList<Container> result = instance.retrieveCart(connection, userID);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of createCart method, of class CartDaoImpl.
     */
    @Test
    public void testCreateCart_Connection_Cart() throws Exception {
        System.out.println("createCart");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Cart cart = new Cart();
        CartDaoImpl instance = new CartDaoImpl();
        cart.setCartContainerID(1L);
        cart.setUserID(1L);
        instance.createCart(connection, cart);
        
        ArrayList<Container> result = instance.retrieveCart(connection, 1L);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of createCart method, of class CartDaoImpl.
     */
    @Test
    public void testCreateCart_3args_2() throws Exception {
        System.out.println("createCart");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Cart cart = new Cart();
        CartDaoImpl instance = new CartDaoImpl();
        Long userID = 1L;
        cart.setCartContainerID(1L);
        cart.setUserID(userID);

        instance.createCart(connection, cart, userID);
        
        ArrayList<Container> result = instance.retrieveCart(connection, 1L);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of retrieveCart method, of class CartDaoImpl.
     */
    @Test
    public void testRetrieveCart() throws Exception {
        System.out.println("retrieveCart");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Cart cart = new Cart();
        CartDaoImpl instance = new CartDaoImpl();
        cart.setCartContainerID(1L);
        cart.setUserID(1L);
        instance.createCart(connection, cart);
        
        Long userID = 1L;
        ArrayList<Container> result = instance.retrieveCart(connection, userID);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }
    
}
