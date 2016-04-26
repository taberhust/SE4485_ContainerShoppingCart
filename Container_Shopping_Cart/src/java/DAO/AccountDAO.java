package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Account;

/**
 * AccountDAO interface
 * 
 * @author matt & kevin
 */
public interface AccountDAO {
    
    Account createAccount(Connection connection, Account account) throws SQLException;
        
    Account retrieveAccount(Connection connection, String username) throws SQLException;
    
    void deleteAccount(Connection connection, String username) throws SQLException;
    
}