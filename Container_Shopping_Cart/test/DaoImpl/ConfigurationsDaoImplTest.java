package DaoImpl;

import DBConnection.DBConnection;
import Entity.Configurations;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class ConfigurationsDaoImplTest {

    /**
     * Test of createConfigurations method, of class ConfigurationsDaoImpl.
     */
    @Test
    public void testCreateConfigurations_Connection_Configurations() throws Exception {
        System.out.println("createConfigurations");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Configurations configurations = new Configurations();
        ConfigurationsDaoImpl instance = new ConfigurationsDaoImpl();
        configurations.setConfigurationID(1L);
        configurations.setContainerID(1L);
        
        instance.createConfigurations(connection, configurations);
        
        ArrayList<Configurations> result = instance.getConfigurations(connection, 1L);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of getConfigurations method, of class ConfigurationsDaoImpl.
     */
    @Test
    public void testGetConfigurations() throws Exception {
        System.out.println("getConfigurations");
        Connection connection = DBConnection.getDataSource().getConnection();
        Long containerID = 1L;
        ConfigurationsDaoImpl instance = new ConfigurationsDaoImpl();
        
        ArrayList<Configurations> result = instance.getConfigurations(connection, containerID);
        assertNotNull(result);
    }

    /**
     * Test of createConfigurations method, of class ConfigurationsDaoImpl.
     */
    @Test
    public void testCreateConfigurations_3args() throws Exception {
        System.out.println("createConfigurations");
        Connection connection = null;
        Configurations configurations = null;
        Long containerID = null;
        ConfigurationsDaoImpl instance = new ConfigurationsDaoImpl();
        Configurations expResult = null;
        Configurations result = instance.createConfigurations(connection, configurations, containerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveConfigurations method, of class ConfigurationsDaoImpl.
     */
    @Test
    public void testRetrieveConfigurations() throws Exception {
        System.out.println("retrieveConfigurations");
        Connection connection = null;
        Long containerID = null;
        ConfigurationsDaoImpl instance = new ConfigurationsDaoImpl();
        Configurations expResult = null;
        Configurations result = instance.retrieveConfigurations(connection, containerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteConfigsForContainer method, of class ConfigurationsDaoImpl.
     */
    @Test
    public void testDeleteConfigsForContainer() throws Exception {
        System.out.println("deleteConfigsForContainer");
        Connection connection = null;
        Long containerID = null;
        ConfigurationsDaoImpl instance = new ConfigurationsDaoImpl();
        instance.deleteConfigsForContainer(connection, containerID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
