package DaoImpl;

import Entity.Configurations;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
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
        Connection connection = null;
        Configurations configurations = null;
        ConfigurationsDaoImpl instance = new ConfigurationsDaoImpl();
        Configurations expResult = null;
        Configurations result = instance.createConfigurations(connection, configurations);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfigurations method, of class ConfigurationsDaoImpl.
     */
    @Test
    public void testGetConfigurations() throws Exception {
        System.out.println("getConfigurations");
        Connection connection = null;
        Long containerID = null;
        ConfigurationsDaoImpl instance = new ConfigurationsDaoImpl();
        ArrayList<Configurations> expResult = null;
        ArrayList<Configurations> result = instance.getConfigurations(connection, containerID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
