/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import DBConnection.DBConnection;
import Entity.Configuration;
import java.sql.Connection;
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
public class ConfigurationDaoImplTest {

    /**
     * Test of createConfiguration method, of class ConfigurationDaoImpl.
     */
    @Test
    public void testCreateConfiguration() throws Exception {
        System.out.println("createConfiguration");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        ConfigurationDaoImpl instance = new ConfigurationDaoImpl();
        Configuration configuration = new Configuration();
        configuration.setDefaultArg1("defaultArg1");
        configuration.setDefaultArg2("defaultArg2");
        configuration.setDefaultType("defaultType");
        configuration.setDisplayName("displayName");
        
        Configuration result = instance.createConfiguration(connection, configuration);
        assertNotNull(result.getConfigurationID());
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of createConfigurationFT method, of class ConfigurationDaoImpl.
     */
    @Test
    public void testCreateConfigurationFT() throws Exception {
        System.out.println("createConfigurationFT");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Configuration configuration = new Configuration();
        configuration.setConfigurationID(1337L);
        configuration.setDefaultArg1("defaultArg1");
        configuration.setDefaultArg2("defaultArg2");
        configuration.setDefaultType("defaultType");
        configuration.setDisplayName("displayName");
        ConfigurationDaoImpl instance = new ConfigurationDaoImpl();
        
        instance.createConfigurationFT(connection, configuration);
        
        Configuration result = instance.getConfiguration(connection, 1337L);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of getConfiguration method, of class ConfigurationDaoImpl.
     */
    @Test
    public void testGetConfiguration() throws Exception {
        System.out.println("getConfiguration");
        Connection connection = DBConnection.getDataSource().getConnection();
        
        Long configID = 1L;
        ConfigurationDaoImpl instance = new ConfigurationDaoImpl();
        Configuration result = instance.getConfiguration(connection, configID);
        assertNotNull(result);
    }
    
}
