/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import DBConnection.DBConnection;
import Entity.Component;
import java.sql.Connection;
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
public class ComponentDaoImplTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of addComponentFT method, of class ComponentDaoImpl.
     */
    @Test
    public void testAddComponent() throws Exception {
        System.out.println("addComponent");
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Component component = new Component();
        component.setComponentName("test component");
        component.setComponentType("test type");
        component.setImageID("1337");
        component.setVersion("test version");
        
        ComponentDaoImpl instance = new ComponentDaoImpl();
        Component result = instance.addComponent(connection, component);
        assertNotNull(result);
        
        component = instance.getComponent(connection, result.getComponentID());
        assertNotNull(component);
        
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of getComponent method, of class ComponentDaoImpl.
     */
    @Test
    public void testGetComponent() throws Exception {
        System.out.println("getComponent");
        Connection connection = DBConnection.getDataSource().getConnection();
        Long id = 1L;
        ComponentDaoImpl instance = new ComponentDaoImpl();
        Component result = instance.getComponent(connection, id);
        assertNotNull(result);
    }
    
    @Test
    public void testDeleteComponent() throws Exception {
        System.out.println("deleteComponent");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        // Test delete container
        Long id = 2L;
        ComponentDaoImpl instance = new ComponentDaoImpl();
        boolean result = instance.deleteComponent(connection, id);
        assertTrue(result);
        
        // Verify container was deleted
        Component component = instance.getComponent(connection, id);
        assertNull(component);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of addComponentFT method, of class ComponentDaoImpl.
     */
    @Test
    public void testAddComponentFT() throws Exception {
        System.out.println("addComponent");
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Component component = new Component();
        component.setComponentID(1337L);
        component.setComponentName("test component");
        component.setComponentType("test type");
        component.setImageID("1337");
        component.setVersion("test version");
        
        ComponentDaoImpl instance = new ComponentDaoImpl();
        Component result = instance.addComponentFT(connection, component);
        assertNotNull(result);
        
        component = instance.getComponent(connection, 1337L);
        assertNotNull(component);
        
        connection.rollback();
        connection.setAutoCommit(true);
    }
    
}
