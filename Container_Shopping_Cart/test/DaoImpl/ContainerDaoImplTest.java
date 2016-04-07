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
    public void testRetrieveAllContainers_Connection() throws Exception {
        System.out.println("retrieveContainer");
        Connection connection = DBConnection.getDataSource().getConnection();
        ContainerDaoImpl instance = new ContainerDaoImpl();
        ArrayList<Container> result = instance.retrieveAllContainers(connection);
        assertNotNull(result);
    }

    /**
     * Test of retrieveContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testRetrieveContainer_Connection_String() throws Exception {
        System.out.println("retrieveContainer");
        Connection connection = DBConnection.getDataSource().getConnection();
        String id = "4dc735ed4bb4";
        ContainerDaoImpl instance = new ContainerDaoImpl();
        Container result = instance.retrieveContainer(connection, id);
        assertNotNull(result);
    }
    
    @Test
    public void testRetrieveContainerByCategory_Connection_String() throws Exception {
        System.out.println("retrieveContainer");
        Connection connection = DBConnection.getDataSource().getConnection();
        ContainerDaoImpl instance = new ContainerDaoImpl();
        String category = "category4";
        ArrayList<Container> result = instance.retrieveContainersByCategory(connection, category);
        assertNotNull(result);
    }
    
    @Test
    public void testRetrieveContainerByName_Connection_String() throws Exception {
        System.out.println("retrieveContainer");
        Connection connection = DBConnection.getDataSource().getConnection();
        ContainerDaoImpl instance = new ContainerDaoImpl();
        String name = "containerName7";
        ArrayList<Container> result = instance.retrieveContainersByName(connection, name);
        assertNotNull(result);
    }
    
    @Test
    public void testRetrieveContainerByProductFamily_Connection_String() throws Exception {
        System.out.println("retrieveContainer");
        Connection connection = DBConnection.getDataSource().getConnection();
        ContainerDaoImpl instance = new ContainerDaoImpl();
        String category = "productFamily6";
        ArrayList<Container> result = instance.retrieveContainersByProductFamily(connection, category);
        assertNotNull(result);
    }
    
    /**
     * Test of addContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testAddContainer() throws Exception {
        System.out.println("addContainer");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        // Test add container
        ContainerDaoImpl instance = new ContainerDaoImpl();
        Container container = new Container();
        container.setContainerID(Long.valueOf("1234567"));
        container.setDockerID("DockerID");
        container.setDockerName("DockerName");
        container.setContainerName("ContainerName");
        container.setPathToIcon(null);
        container.setCategory("Test");
        container.setProductFamily("Test Container 1.0");
        container.setVersion("1.0");
        boolean result = instance.addContainer(connection, container);
        assertTrue(result);
        
        // Test if added container exists
        String id = "000000000000";
        container = instance.retrieveContainer(connection, id);
        assertNotNull(container);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of editContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testEditContainer() throws Exception {
        System.out.println("editContainer");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        // Test edit container
        String id = "2";
        ContainerDaoImpl instance = new ContainerDaoImpl();
        Container container = new Container();
        container.setContainerID(Long.valueOf("2"));
        container.setDockerID("DockerID");
        container.setDockerName("DockerName");
        container.setContainerName("ContainerName");
        container.setPathToIcon(null);
        container.setCategory("Test");
        container.setProductFamily("Test Container 1.0");
        container.setVersion("1.0");
        boolean result = instance.editContainer(connection, id, container);
        assertTrue(result);
                
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of deleteContainer method, of class ContainerDaoImpl.
     */
    @Test
    public void testDeleteContainer() throws Exception {
        System.out.println("deleteContainer");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        // Test delete container
        String id = "3";
        ContainerDaoImpl instance = new ContainerDaoImpl();
        boolean result = instance.deleteContainer(connection, id);
        assertTrue(result);
        
        // Verify container was deleted
        Container container = instance.retrieveContainer(connection, id);
        assertNull(container);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }
    
}
