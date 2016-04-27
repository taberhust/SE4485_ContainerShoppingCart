/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import DBConnection.DBConnection;
import Entity.Account;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matt
 */
public class AccountDaoImplTest {

    /**
     * Test of createAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testCreateAccount() throws Exception {
        System.out.println("createAccount");
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        
        Account account = new Account();
        AccountDaoImpl instance = new AccountDaoImpl();
        account.setUserID(666L);
        account.setUserName("tCAUserName");
        account.setFirstName("tCAFirtName");
        account.setLastName("tCALastName");
        account.setPrivilege("tCAPrivilege");
        account.setPassword("tCAPassword");
        instance.createAccount(connection, account);
        assertNotNull(instance);
        
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of retrieveAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testRetrieveAccount() throws Exception {
        System.out.println("retrieveAccount");
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String username = "a";
        AccountDaoImpl instance = new AccountDaoImpl();
        Account result = instance.retrieveAccount(connection, username);
        assertNotNull(result);
        
        connection.rollback();
        connection.setAutoCommit(true);
    }

    /**
     * Test of deleteAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testDeleteAccount() throws Exception {
        System.out.println("deleteAccount");
        Connection connection = DBConnection.getDataSource().getConnection();
        connection.setAutoCommit(false);
        String username = "userName1";
        AccountDaoImpl instance = new AccountDaoImpl();
        boolean deleted = instance.deleteAccount(connection, username);
        assertTrue(deleted);
        
        Account accountDeleted = instance.retrieveAccount(connection, username);
        assertNull(accountDeleted);
        
        connection.rollback();
        connection.setAutoCommit(true);
    }
    
}
