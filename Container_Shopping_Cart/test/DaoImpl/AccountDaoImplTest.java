/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Entity.Account;
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
public class AccountDaoImplTest {

    /**
     * Test of createAccountFT method, of class AccountDaoImpl.
     */
    @Test
    public void testCreateAccountFT() throws Exception {
        System.out.println("createAccountFT");
        Connection connection = null;
        Account account = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
        Account result = instance.createAccountFT(connection, account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testCreateAccount() throws Exception {
        System.out.println("createAccount");
        Connection connection = null;
        Account account = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
        Account result = instance.createAccount(connection, account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testRetrieveAccount() throws Exception {
        System.out.println("retrieveAccount");
        Connection connection = null;
        String username = "";
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
        Account result = instance.retrieveAccount(connection, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testDeleteAccount() throws Exception {
        System.out.println("deleteAccount");
        Connection connection = null;
        String username = "";
        AccountDaoImpl instance = new AccountDaoImpl();
        instance.deleteAccount(connection, username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
