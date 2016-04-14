/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DBConnection.DBConnection;
import Entity.Container;
import Entity.Items;
import Entity.Purchase;
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
 * @author kevin
 */
public class DAOInterfaceTest {

    /**
     * Test of retrieveAllContainers method, of class DAOInterface.
     */
    @Test
    public void testRetrieveAllContainers() throws Exception {
        System.out.println("retrieveAllContainers");
        Connection connection = DBConnection.getDataSource().getConnection();
        DAOInterface instance = new DAOInterface();
        ArrayList<Container> result = instance.retrieveAllContainers(connection);
        assertNotNull(result);
    }

    /**
     * Test of retrieveContainersByCategory method, of class DAOInterface.
     */
    @Test
    public void testRetrieveContainersByCategory() throws Exception {
        System.out.println("retrieveContainersByCategory");
        Connection connection = DBConnection.getDataSource().getConnection();
        String category = "category1";
        DAOInterface instance = new DAOInterface();
        ArrayList<Container> result = instance.retrieveContainersByCategory(connection, category);
        assertNotNull(result);
    }

    /**
     * Test of retrieveContainersByName method, of class DAOInterface.
     */
    @Test
    public void testRetrieveContainersByName() throws Exception {
        System.out.println("retrieveContainersByName");
        Connection connection = DBConnection.getDataSource().getConnection();
        String name = "containerName1";
        DAOInterface instance = new DAOInterface();
        ArrayList<Container> result = instance.retrieveContainersByName(connection, name);
        assertNotNull(result);
    }

    /**
     * Test of retrieveContainersByProductFamily method, of class DAOInterface.
     */
    @Test
    public void testRetrieveContainersByProductFamily() throws Exception {
        System.out.println("retrieveContainersByProductFamily");
        Connection connection = DBConnection.getDataSource().getConnection();
        String family = "productFamily1";
        DAOInterface instance = new DAOInterface();
        ArrayList<Container> result = instance.retrieveContainersByProductFamily(connection, family);
        assertNotNull(result);
    }
    
    /**
     * Test of retrieveItems method, of class DAOInterface.
     */
    @Test
    public void testRetrieveItems() throws Exception {
        System.out.println("getItems");
        Connection connection = DBConnection.getDataSource().getConnection();
        Long purchaseID = 2L;
        DAOInterface instance = new DAOInterface();
        ArrayList<Items> result = instance.retrieveItems(connection, purchaseID);
        assertNotNull(result);
    }
    
    /**
     * Test of retrievePurchases method, of class DAOInterface.
     */
    @Test
    public void testRetrievePurchases() throws Exception {
        System.out.println("retrievePurchases");
        Connection connection = DBConnection.getDataSource().getConnection();
        Long userID = 5L;
        DAOInterface instance = new DAOInterface();
        ArrayList<Purchase> result = instance.retrievePurchases(connection, userID);
        assertNotNull(result);
    }
    
    @Test
    public void testAddContainerToCart() throws Exception {
        System.out.println("addContainerToCart");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Long userID = 2L;
        DAOInterface instance = new DAOInterface();
        Container container = instance.retrieveContainer(connection, 2L);
        Container result = instance.addContainerToCart(connection, container, userID);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of addPurchase method, of class DAOInterface.
     */
    @Test
    public void testAddPurchase() throws Exception {
        System.out.println("addPurchase");
        Connection connection = DBConnection.getDataSource().getConnection();
        Purchase purchase = null;
        DAOInterface instance = new DAOInterface();
        Purchase result = instance.addPurchase(connection, purchase);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of addContainer method, of class DAOInterface.
     */
    @Test
    public void testAddContainer() throws Exception {
        System.out.println("addContainer");
        Connection connection = null;
        Container container = null;
        DAOInterface instance = new DAOInterface();
        Container expResult = null;
        Container result = instance.addContainer(connection, container);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveContainer method, of class DAOInterface.
     */
    @Test
    public void testRetrieveContainer() throws Exception {
        System.out.println("retrieveContainer");
        Connection connection = DBConnection.getDataSource().getConnection();
        Long containerID = 1L;
        DAOInterface instance = new DAOInterface();
        Container result = instance.retrieveContainer(connection, containerID);
        assertNotNull(result);
    }

    /**
     * Test of editContainer method, of class DAOInterface.
     */
    @Test
    public void testEditContainer() throws Exception {
        System.out.println("editContainer");
        Connection connection = null;
        Long id = null;
        Container container = null;
        DAOInterface instance = new DAOInterface();
        boolean expResult = false;
        boolean result = instance.editContainer(connection, id, container);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteContainer method, of class DAOInterface.
     */
    @Test
    public void testDeleteContainer() throws Exception {
        System.out.println("deleteContainer");
        Connection connection = null;
        Long id = null;
        DAOInterface instance = new DAOInterface();
        boolean expResult = false;
        boolean result = instance.deleteContainer(connection, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
