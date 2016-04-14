/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import DBConnection.DBConnection;
import Entity.Purchase;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
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
public class PurchaseDaoImplTest {

    /**
     * Test of createPurchase method, of class PurchaseDaoImpl.
     */
    @Test
    public void testCreatePurchase() throws Exception {
        System.out.println("createPurchase");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Purchase purchase = new Purchase();
        purchase.setItems(null);
        purchase.setPurchaseID(1337L);
        purchase.setUserID(2L);
        
        Random rnGen = new Random();
        Calendar cal = GregorianCalendar.getInstance();
        int dateOffset = rnGen.nextInt(10*365);
	cal.add(Calendar.DAY_OF_YEAR, dateOffset);
	purchase.setTimeOfPurchase(new java.sql.Date(cal.getTimeInMillis()));
        
        PurchaseDaoImpl instance = new PurchaseDaoImpl();
        Purchase result = instance.createPurchase(connection, purchase);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }
    
    /**
     * Test of addPurchase method, of class PurchaseDaoImpl.
     */
    @Test
    public void testAddPurchase() throws Exception {
        System.out.println("AddPurchase");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Purchase purchase = new Purchase();
        purchase.setItems(null);
        purchase.setUserID(2L);
        
        Random rnGen = new Random();
        Calendar cal = GregorianCalendar.getInstance();
        int dateOffset = rnGen.nextInt(10*365);
	cal.add(Calendar.DAY_OF_YEAR, dateOffset);
	purchase.setTimeOfPurchase(new java.sql.Date(cal.getTimeInMillis()));
        
        PurchaseDaoImpl instance = new PurchaseDaoImpl();
        Purchase result = instance.addPurchase(connection, purchase);
        assertNotNull(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of retrievePurchases method, of class PurchaseDaoImpl.
     */
    @Test
    public void testRetrievePurchases() throws Exception {
        System.out.println("retrievePurchases");
        Connection connection = DBConnection.getDataSource().getConnection();
        Long userID = 2L;
        PurchaseDaoImpl instance = new PurchaseDaoImpl();
        ArrayList<Purchase> result = instance.retrievePurchases(connection, userID);
        assertNotNull(result);
    }

    /**
     * Test of editPurchase method, of class PurchaseDaoImpl.
     */
    @Test
    public void testEditPurchase() throws Exception {
        System.out.println("editPurchase");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Purchase purchase = new Purchase();
        purchase.setItems(null);
        purchase.setPurchaseID(35L);
        purchase.setUserID(2L);
        
        Random rnGen = new Random();
        Calendar cal = GregorianCalendar.getInstance();
        int dateOffset = rnGen.nextInt(10*365);
	cal.add(Calendar.DAY_OF_YEAR, dateOffset);
	purchase.setTimeOfPurchase(new java.sql.Date(cal.getTimeInMillis()));
        
        PurchaseDaoImpl instance = new PurchaseDaoImpl();
        boolean result = instance.editPurchase(connection, purchase);
        assertTrue(result);
                
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of deletePurchase method, of class PurchaseDaoImpl.
     */
    @Test
    public void testDeletePurchase() throws Exception {
        System.out.println("deletePurchase");
        // Set up connection to not make changes
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Long purchaseID = 1L;
        PurchaseDaoImpl instance = new PurchaseDaoImpl();
        boolean result = instance.deletePurchase(connection, purchaseID);
        assertTrue(result);
        
        // Rollback any changes this test made
        connection.rollback();
        connection.setAutoCommit(true);
    }
    
}
