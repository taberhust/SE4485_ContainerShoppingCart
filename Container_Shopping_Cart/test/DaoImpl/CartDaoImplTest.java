/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

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
        Connection connection = null;
        Long userID = null;
        Container container = null;
        CartDaoImpl instance = new CartDaoImpl();
        instance.createCart(connection, userID, container);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCart method, of class CartDaoImpl.
     */
    @Test
    public void testCreateCart_Connection_Cart() throws Exception {
        System.out.println("createCart");
        Connection connection = null;
        Cart cart = null;
        CartDaoImpl instance = new CartDaoImpl();
        Cart expResult = null;
        Cart result = instance.createCart(connection, cart);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCart method, of class CartDaoImpl.
     */
    @Test
    public void testCreateCart_3args_2() throws Exception {
        System.out.println("createCart");
        Connection connection = null;
        Cart cart = null;
        Long userID = null;
        CartDaoImpl instance = new CartDaoImpl();
        Cart expResult = null;
        Cart result = instance.createCart(connection, cart, userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveCart method, of class CartDaoImpl.
     */
    @Test
    public void testRetrieveCart() throws Exception {
        System.out.println("retrieveCart");
        Connection connection = null;
        Long userID = null;
        CartDaoImpl instance = new CartDaoImpl();
        ArrayList<Container> expResult = null;
        ArrayList<Container> result = instance.retrieveCart(connection, userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
