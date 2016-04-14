/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import DBConnection.DBConnection;
import Entity.Items;
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
public class ItemsDaoImplTest {

    /**
     * Test of createItems method, of class ItemsDaoImpl.
     */
    @Test
    public void testCreateItems() throws Exception {
        System.out.println("createItems");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Items items = new Items();
        items.setContainerID(1L);
        items.setPurchaseID(1L);
        ItemsDaoImpl instance = new ItemsDaoImpl();
        Items result = instance.createItems(connection, items);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of getItems method, of class ItemsDaoImpl.
     */
    @Test
    public void testGetItems() throws Exception {
        System.out.println("getItems");
        Connection connection = DBConnection.getDataSource().getConnection();
        Long purchaseID = 1L;
        ItemsDaoImpl instance = new ItemsDaoImpl();
        ArrayList<Items> result = instance.getItems(connection, purchaseID);
        assertNotNull(result);
    }    
}
