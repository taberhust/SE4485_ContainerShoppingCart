/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import DBConnection.DBConnection;
import Entity.Components;
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
public class ComponentsDaoImplTest {

    /**
     * Test of addComponents method, of class ComponentsDaoImpl.
     */
    @Test
    public void testAddComponents() throws Exception {
        System.out.println("createComponents");
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Components components = new Components();
        ComponentsDaoImpl instance = new ComponentsDaoImpl();
        components.setComponentID(3L);
        components.setContainerID(1L);
        
        Components result = instance.addComponents(connection, components);
        assertNotNull(result);
                
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of getComponents method, of class ComponentsDaoImpl.
     */
    @Test
    public void testGetComponents() throws Exception {
        System.out.println("retrieveComponents");
        Connection connection = DBConnection.getDataSource().getConnection();
        Long containerID = 1L;
        ComponentsDaoImpl instance = new ComponentsDaoImpl();
        ArrayList<Components> result = instance.getComponents(connection, containerID);
        assertNotNull(result);
    }

    /**
     * Test of deleteComponentsFromContainer method, of class ComponentsDaoImpl.
     */
    @Test
    public void testDeleteComponentsFromContainer() throws Exception {
        System.out.println("deleteComponentsFromContainer");
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Long containerID = 2L;
        Long componentID = 29L;
        ComponentsDaoImpl instance = new ComponentsDaoImpl();
        boolean result = instance.deleteComponentsFromContainer(connection, containerID, componentID);
        assertTrue(result);
        
        ArrayList<Components> resultArr = instance.getComponents(connection, containerID);
        boolean test = false;
        if(resultArr != null) {
            for(Components x : resultArr) {
                if(x.getComponentID() == 1337L) {
                    test = true;
                }
            }
        }
        
        assertFalse(test);
        
        connection.rollback();
        connection.setAutoCommit(true);
    }
    
}
