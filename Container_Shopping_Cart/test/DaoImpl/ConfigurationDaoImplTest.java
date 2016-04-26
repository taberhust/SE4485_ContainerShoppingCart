/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Entity.Configuration;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matt
 */
public class ConfigurationDaoImplTest {

    /**
     * Test of createConfiguration method, of class ConfigurationDaoImpl.
     */
    @Test
    public void testCreateConfiguration() throws Exception {
        System.out.println("createConfiguration");
        Connection connection = null;
        Configuration configuration = null;
        ConfigurationDaoImpl instance = new ConfigurationDaoImpl();
        Configuration expResult = null;
        Configuration result = instance.createConfiguration(connection, configuration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createConfigurationFT method, of class ConfigurationDaoImpl.
     */
    @Test
    public void testCreateConfigurationFT() throws Exception {
        System.out.println("createConfigurationFT");
        Connection connection = null;
        Configuration configuration = null;
        ConfigurationDaoImpl instance = new ConfigurationDaoImpl();
        Configuration expResult = null;
        Configuration result = instance.createConfigurationFT(connection, configuration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfiguration method, of class ConfigurationDaoImpl.
     */
    @Test
    public void testGetConfiguration() throws Exception {
        System.out.println("getConfiguration");
        Connection connection = null;
        Long configID = null;
        ConfigurationDaoImpl instance = new ConfigurationDaoImpl();
        Configuration expResult = null;
        Configuration result = instance.getConfiguration(connection, configID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
