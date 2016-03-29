/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Entity.Container;
import DBConnection.DBConnection;
import java.io.File;
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
public class ContainerDaoImplTest {
    
    public ContainerDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of retrieveContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testRetrieveContainer_Connection() throws Exception {
        System.out.println("retrieveContainer");
        Connection connection = DBConnection.getDataSource().getConnection();
        ContainerDaoImpl instance = new ContainerDaoImpl();
        ArrayList<Container> expResult = null;
        ArrayList<Container> result = instance.retrieveContainer(connection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("This test is for debugging. Will always fail.");
    }

    /**
     * Test of retrieveContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testRetrieveContainer_Connection_String() throws Exception {
        System.out.println("retrieveContainer");
        Connection connection = null;
        String id = "";
        ContainerDaoImpl instance = new ContainerDaoImpl();
        Container expResult = null;
        Container result = instance.retrieveContainer(connection, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testAddContainer() throws Exception {
        System.out.println("addContainer");
        Connection connection = null;
        File image = null;
        ContainerDaoImpl instance = new ContainerDaoImpl();
        Container expResult = null;
        Container result = instance.addContainer(connection, image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testEditContainer() throws Exception {
        System.out.println("editContainer");
        Connection connection = null;
        String id = "";
        File image = null;
        ContainerDaoImpl instance = new ContainerDaoImpl();
        Container expResult = null;
        Container result = instance.editContainer(connection, id, image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testDeleteContainer() {
        System.out.println("deleteContainer");
        Connection connection = null;
        String id = "";
        ContainerDaoImpl instance = new ContainerDaoImpl();
        instance.deleteContainer(connection, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
