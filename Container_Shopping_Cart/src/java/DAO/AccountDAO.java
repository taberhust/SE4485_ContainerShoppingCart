/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Account;

/**
 *@author matt & kevin
 * 
 * This only updates the Account table
 */
public interface AccountDAO {
    
    Account createAccount(Connection connection, Account account) throws SQLException;
    
    Account createAccountFT(Connection connection, Account account) throws SQLException;
    
    Account retrieveAccount(Connection connection, String username) throws SQLException;
    
    void deleteAccount(Connection connection, String username) throws SQLException;
    
}